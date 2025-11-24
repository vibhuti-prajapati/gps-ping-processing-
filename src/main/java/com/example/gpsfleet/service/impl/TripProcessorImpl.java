package com.example.gpsfleet.service.impl;


import com.example.gpsfleet.dto.request.DeviceState;
import com.example.gpsfleet.dto.response.AlertDriverDto;
import com.example.gpsfleet.dto.response.AlertDto;
import com.example.gpsfleet.entity.*;
import com.example.gpsfleet.repository.AlertRepository;
import com.example.gpsfleet.repository.TripEventRepository;
import com.example.gpsfleet.repository.TripRepository;
import com.example.gpsfleet.service.TripProcessor;
import com.example.gpsfleet.repository.GpsPingRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

@Component
public class TripProcessorImpl implements TripProcessor {
    private final Map<String, DeviceState> deviceStateMap = new ConcurrentHashMap<>();

    private final LinkedBlockingQueue<GpsPing> queue = new LinkedBlockingQueue<>(10000);
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private final TripEventRepository tripEventRepository;
    private volatile boolean running = false;
    private final ReverseGeoCodingService reverseGeocodingService;
    private final TripRepository tripRepository;
    private final AlertRepository alertRepository;
    @Autowired
    public TripProcessorImpl(TripRepository tripRepository, AlertRepository alertRepository, ReverseGeoCodingService reverseGeocodingService, TripEventRepository tripEventRepository) {
        this.tripRepository = tripRepository;
        this.alertRepository = alertRepository;
        this.reverseGeocodingService = reverseGeocodingService;
        this.tripEventRepository = tripEventRepository;
    }

    @Value("${trip.processor.enabled:true}")
    private boolean autoStart;

    @Override
    public void enqueue(GpsPing ping) {
        queue.offer(ping);
    }


    @PostConstruct
    @Override
    public void start() {
        if (!autoStart) {
            System.out.println("TripProcessor auto-start disabled for tests.");
            return;
        }
        running = true;
        worker.submit(() -> {
            while (running || !queue.isEmpty()) {
                try {
                    GpsPing ping = queue.poll(1, TimeUnit.SECONDS);
                    if (ping == null) continue;
                    process(ping);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void process(GpsPing ping) {
        String deviceId = ping.getDevice().getDeviceId();

        // get existing state or create a new one
        DeviceState state = deviceStateMap.get(deviceId);
        if (state == null) {
            state = new DeviceState();
            deviceStateMap.put(deviceId, state);
        }
        GpsPing lastPing = state.getLastPing();

        double speed = safeSpeed(ping);

        // movementCounter for THIS ping so checkTripStart sees it
        int movementCounter = state.getMovementCounter();
        if (speed > 2.0) {
            movementCounter++;
        } else {
            movementCounter = 0;
        }
        state.setMovementCounter(movementCounter);

        //distance from last known coords
        double distanceFromLast = 0.0;
        if (state.getLastLat() != null && state.getLastLon() != null) {
            distanceFromLast = DistanceCalcService.haversine(
                    ping.getLat(), ping.getLon(),
                    state.getLastLat(), state.getLastLon()
            );
            System.out.println("distance from last :" + distanceFromLast);
        }

        checkTripStart(ping, state, distanceFromLast, speed);
        checkTripEnd(ping, state, distanceFromLast,speed);
        checkOverspeed(ping, state, speed);
        checkIdleTime(ping, state, speed, distanceFromLast);
        // update with current ping
        updateState(ping,state);
        state.setLastPing(ping);

    }

    private double safeSpeed(GpsPing ping) {
        return ping.getSpeedKmh() == null ? 0.0 : ping.getSpeedKmh();
    }

    private void updateState(GpsPing ping, DeviceState state) {
        if (state.getLastLat() != null && state.getLastLon() != null) {
            double distance = DistanceCalcService.haversine(
                    ping.getLat(), ping.getLon(),
                    state.getLastLat(), state.getLastLon()
            );
            if (distance > 10) {
                state.setLastMovementTime(ping.getSentAt());
                System.out.println("last movement time: " + state.getLastMovementTime());
            }
        }

        state.setLastLat(ping.getLat());
        state.setLastLon(ping.getLon());
    }

    private void checkIdleTime(GpsPing ping, DeviceState state,double distanceFromLast, double speed) {
        // idle time -> true
        // - vehicle at the same spot for more than 1 minute
        // - lat lon are the same or unusually very close (slow moving because of traffic)
        // - vehicle speed < 2 km/hr
        // vehicle in trip ->trip event +alert
        // vehicle not in trip -> alert
        boolean isSlow = speed < 2;

        if (isSlow) {
            if (!state.isIdle()) {
                state.setIdle(true);
                state.setIdleStartTime(ping.getSentAt());
            }

            if (state.getIdleStartTime().plusSeconds(60).isBefore(ping.getSentAt())) {

                if(state.getCurrentIdleEventId()==null) {

                    if(state.isInTrip()) {
                        Trip trip = tripRepository.findById(state.getCurrentTripId()).orElse(null);
                        TripEvent tripEvent = new TripEvent();
                        tripEvent.setTripId(trip);
                        tripEvent.setEventType(EventType.IDLE);
                        tripEvent.setStartTime(state.getIdleStartTime());
                        tripEvent.setMetadata(
                                "{\"address\": \"" + reverseGeocodingService.getAddress(ping.getLat(), ping.getLon()).replace("\"", "\\\"") + "\"}"
                        );
                        tripEventRepository.save(tripEvent);
                        state.setCurrentIdleEventId(tripEvent.getEvent_id());
                    }
                    Alert alert = new Alert();
                    alert.setVehicle(ping.getDevice().getVehicle());
                    alert.setPing(ping);
                    alert.setAlertType(AlertType.IDLE);
                    alert.setMessage("Vehicle idle for 60+ seconds");
                    alertRepository.save(alert);
                }
            }
        }else{
            if (state.isIdle() && state.getCurrentIdleEventId()!=null && state.getIdleStartTime()!=null) {
                if(ping.getSentAt().isAfter(state.getIdleStartTime())){
                    TripEvent tripEvent = tripEventRepository.findById(state.getCurrentIdleEventId()).orElse(null);
                    if(tripEvent!=null){
                        tripEvent.setEndTime(ping.getSentAt());
                        tripEventRepository.save(tripEvent);
                    }
                    state.setCurrentIdleEventId(null);
                }
                state.setIdle(false);
                state.setIdleStartTime(null);
            }
        }
    }

    private void checkTripEnd(GpsPing ping, DeviceState state,double distanceFromLast, double speed) {
        System.out.println("check if in trip : " +state.isInTrip());
        if (state.isInTrip()) {
            boolean stationary = ping.getSpeedKmh() != null && ping.getSpeedKmh() < 2;
            System.out.println("stationary: " + stationary);
            if (stationary) {
                if (state.getLastMovementTime().plusSeconds(360).isBefore(ping.getSentAt())) {
                    Trip trip = tripRepository.findById(state.getCurrentTripId()).orElseThrow();
                    System.out.println("trip coordinates start : " + trip.getStartLat() +"," + trip.getStartLon());
                    double distance = DistanceCalcService.haversine( trip.getStartLat(), trip.getStartLon() , ping.getLat(), ping.getLon());
                    trip.setTotalDistanceM(distance/1000);
                    trip.setEndTime(ping.getSentAt());
                    trip.setEndLat(ping.getLat());
                    trip.setEndLon(ping.getLon());

                    tripRepository.save(trip);
                    System.out.println(trip.getTotalDistanceM());
                    state.setInTrip(false);
                    state.setCurrentTripId(null);
                    state.setMovementCounter(0);
                    System.out.println("⛔ Trip ended for device " + ping.getDevice().getDeviceId());
                }
            } else {
                state.setLastMovementTime(Instant.now());
            }
        }

    }

    private void checkTripStart(GpsPing ping, DeviceState state, double distanceFromLast, double speed) {
        if (!state.isInTrip()
                && state.getMovementCounter() >= 2
                && speed > 2.0 ) {
                if(distanceFromLast > 30) {
                    Trip trip = new Trip();
                    trip.setVehicle(ping.getDevice().getVehicle());
                    trip.setDevice(ping.getDevice());
                    trip.setStartTime(ping.getSentAt());
                    trip.setStartLat(ping.getLat());
                    trip.setStartLon(ping.getLon());
                    trip.setDriver(ping.getDevice().getVehicle().getDriverId());
                    Trip saved = tripRepository.save(trip);

                    state.setInTrip(true);
                    state.setCurrentTripId(saved.getTripId());
                    state.setLastMovementTime(ping.getSentAt());

                    System.out.println("➡️ Trip started for device " + ping.getDevice().getDeviceId());
            }
        }
    }

    private void checkOverspeed(GpsPing ping, DeviceState state, double speed) {
        if(ping.getSpeedKmh() >120) {
            //manual speed limit check
            Alert alert = new Alert();
            alert.setMessage("overspeed: "+ping.getSpeedKmh());
            alert.setVehicle(ping.getDevice().getVehicle());
            alert.setPing(ping);
            alert.setAlertType(AlertType.OVERSPEED);
            alertRepository.save(alert);
        }
}

    @PreDestroy
    @Override
    public void stop() {
        running = false;
        worker.shutdown();
        try {
            worker.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {}
    }

    void processDirect(GpsPing ping) {
        process(ping);
    }

}