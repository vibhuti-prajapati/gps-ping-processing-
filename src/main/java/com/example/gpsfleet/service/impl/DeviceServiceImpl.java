package com.example.gpsfleet.service.impl;

import com.example.gpsfleet.dto.request.CreateDeviceDto;
import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.Vehicle;
import com.example.gpsfleet.repository.DeviceRepository;
import com.example.gpsfleet.repository.DriverRepository;
import com.example.gpsfleet.repository.VehicleRepository;
import com.example.gpsfleet.service.DeviceService;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.deviceRepository = deviceRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public String createDevice(CreateDeviceDto deviceDto) {
        Vehicle vehicle = vehicleRepository.findById(deviceDto.vehicleId())
                .orElseThrow(() -> new IllegalArgumentException("vehicle not found"));
        Device device = new Device();
        device.setVehicle(vehicle);
        device.setImei(deviceDto.imei());
        device.setDeviceId(deviceDto.deviceId());
        deviceRepository.save(device);
        return "device added";
    }

    @Override
    public boolean existsById(String deviceId) {
        return false;
    }
}
