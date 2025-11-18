package com.example.gpsfleet.dto.response;

import java.time.Instant;
import java.util.List;

public record TripDetailDto(
        Long tripId,
        Long vehicleId,
        Long driverId,
        Instant startTime,
        Instant endTime,
        double startLat,
        double startLon,
        double endLat,
        double endLon,
        long totalDistanceM,
        long totalDurationS,
        Double avgSpeedKmh,
        List<TripEventDto> events
) {
    @Override
    public Long tripId() {
        return tripId;
    }

    @Override
    public Long vehicleId() {
        return vehicleId;
    }

    @Override
    public Long driverId() {
        return driverId;
    }

    @Override
    public Instant startTime() {
        return startTime;
    }

    @Override
    public Instant endTime() {
        return endTime;
    }

    @Override
    public double startLat() {
        return startLat;
    }

    @Override
    public double startLon() {
        return startLon;
    }

    @Override
    public double endLat() {
        return endLat;
    }

    @Override
    public double endLon() {
        return endLon;
    }

    @Override
    public long totalDistanceM() {
        return totalDistanceM;
    }

    @Override
    public long totalDurationS() {
        return totalDurationS;
    }

    @Override
    public Double avgSpeedKmh() {
        return avgSpeedKmh;
    }

    @Override
    public List<TripEventDto> events() {
        return events;
    }
}
