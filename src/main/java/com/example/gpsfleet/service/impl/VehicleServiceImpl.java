package com.example.gpsfleet.service.impl;
import com.example.gpsfleet.dto.request.CreateVehicleDto;
import com.example.gpsfleet.dto.request.DeviceState;
import com.example.gpsfleet.dto.response.VehicleLocationDto;
import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.Driver;
import com.example.gpsfleet.entity.Fleet;
import com.example.gpsfleet.entity.Vehicle;
import com.example.gpsfleet.repository.*;
import com.example.gpsfleet.service.VehicleService;
import com.example.gpsfleet.util.ReverseGeoCodingService;
import org.springframework.stereotype.Service;
import com.example.gpsfleet.processor.impl.TripProcessorImpl;

@Service
public class VehicleServiceImpl implements VehicleService {


    private final VehicleRepository vehicleRepository;
    private final GpsPingRepository gpsPingRepository;
    private final FleetRepository  fleetRepository;
    private final DriverRepository driverRepository;
    private final TripProcessorImpl tripProcessorImpl;
    private final DeviceRepository deviceRepository;
    private final ReverseGeoCodingService reverseGeocodingService;


    public VehicleServiceImpl(VehicleRepository vehicleRepository, GpsPingRepository gpsPingRepository, FleetRepository fleetRepository, DriverRepository driverRepository, TripProcessorImpl tripProcessorImpl, DeviceRepository deviceRepository, ReverseGeoCodingService reverseGeocodingService
) {
        this.vehicleRepository = vehicleRepository;
        this.gpsPingRepository = gpsPingRepository;
        this.fleetRepository= fleetRepository;
        this.driverRepository = driverRepository;
        this.tripProcessorImpl = tripProcessorImpl;
        this.deviceRepository = deviceRepository;
        this.reverseGeocodingService = reverseGeocodingService;
    }


    @Override
    public String createVehicle(CreateVehicleDto vehicleDto) {

        Fleet fleet = fleetRepository.findById(vehicleDto.fleetId())
                .orElseThrow(() -> new IllegalArgumentException("Fleet not found"));

        Driver driver = driverRepository.findById(vehicleDto.driverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setFleet(fleet);
        vehicle.setDriverId(driver);
        vehicle.setRegNo(vehicleDto.regNo());
        vehicle.setModel(vehicleDto.model());
        vehicleRepository.save(vehicle);

        return "vehicle added";
    }

    @Override
    public boolean existsById(Long fleetId) {
        return false;
    }

    @Override
    public VehicleLocationDto getLastLocation(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        Device device = deviceRepository.findByVehicle(vehicle).orElse(null);
        if(device!=null){
            DeviceState state = tripProcessorImpl.getCurrentState(device.getDeviceId());
            if(state!=null){
                String lastKnownLocation = reverseGeocodingService.getAddress(state.getLastLat(),state.getLastLon());
                VehicleLocationDto liveLocation= new VehicleLocationDto(vehicleId,state.getLastLat(),state.getLastLon(),lastKnownLocation,state.getLastMovementTime(),state.isInTrip(),state.getStatus()) ;
                return liveLocation;
            }
        }
        return null;
    }
}

