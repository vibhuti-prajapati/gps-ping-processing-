package com.example.gpsfleet.service.impl;
import com.example.gpsfleet.dto.response.VehicleLocationDto;
import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.Vehicle;
import com.example.gpsfleet.repository.GpsPingRepository;
import com.example.gpsfleet.repository.VehicleRepository;
import com.example.gpsfleet.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {


    private final VehicleRepository vehicleRepository;
    private final GpsPingRepository gpsPingRepository;


    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, GpsPingRepository gpsPingRepository) {
        this.vehicleRepository = vehicleRepository;
        this.gpsPingRepository = gpsPingRepository;
    }


    @Override
    public VehicleLocationDto getLastLocation(Long vehicleId) {
// naive approach: find device for vehicle and then last ping
        Optional<Vehicle> vOpt = vehicleRepository.findById(vehicleId);
        if (vOpt.isEmpty()) throw new IllegalArgumentException("vehicle not found: " + vehicleId);
        com.example.gpsfleet.entity.Vehicle v = vOpt.get();
// find any device for this vehicle (could be multiple)
//        Optional<Device> deviceOpt = com.example.gpsfleet.repository.DeviceRepositoryHolder.findByVehicleId(deviceRepository(), vehicleId);
// The above is a placeholder. In your project, use DeviceRepository.findByVehicleId...
// For now, to keep this skeleton compile-friendly, we will attempt to find latest ping by device via gpsPingRepository
        throw new UnsupportedOperationException("Implement device->latest ping lookup in VehicleServiceImpl");
    }


    // helper to avoid compile error in template - replace with real DeviceRepository injection
    private com.example.gpsfleet.repository.DeviceRepository deviceRepository() {
        throw new UnsupportedOperationException("inject DeviceRepository here and remove placeholder");
    }
}

