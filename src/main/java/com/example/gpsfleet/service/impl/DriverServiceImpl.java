package com.example.gpsfleet.service.impl;

import com.example.gpsfleet.dto.request.CreateDriverDto;
import com.example.gpsfleet.entity.Driver;
import com.example.gpsfleet.repository.DriverRepository;
import com.example.gpsfleet.repository.FleetRepository;
import com.example.gpsfleet.repository.GpsPingRepository;
import com.example.gpsfleet.repository.VehicleRepository;
import com.example.gpsfleet.service.DriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    private final VehicleRepository vehicleRepository;
    private final GpsPingRepository gpsPingRepository;
    private final FleetRepository  fleetRepository;
    private final DriverRepository driverRepository;

  public DriverServiceImpl(VehicleRepository vehicleRepository, GpsPingRepository gpsPingRepository, FleetRepository fleetRepository, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.gpsPingRepository = gpsPingRepository;
        this.fleetRepository= fleetRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public String createDriver(CreateDriverDto driverDto) {
        driverRepository.save(new Driver(driverDto));
        return "driver added";
    }

    @Override
    public boolean existsById(Long driverId) {
        return false;
    }
}
