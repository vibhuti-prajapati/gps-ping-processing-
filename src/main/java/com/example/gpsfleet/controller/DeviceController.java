package com.example.gpsfleet.controller;

import com.example.gpsfleet.dto.request.CreateDeviceDto;
import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.repository.DriverRepository;
import com.example.gpsfleet.repository.FleetRepository;
import com.example.gpsfleet.repository.GpsPingRepository;
import com.example.gpsfleet.repository.VehicleRepository;
import com.example.gpsfleet.service.impl.DeviceServiceImpl;
import com.example.gpsfleet.service.impl.DriverServiceImpl;
import com.example.gpsfleet.service.impl.VehicleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gps/device")
public class DeviceController {

    private final DeviceServiceImpl deviceService;
    public DeviceController(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/create-device")
    public ResponseEntity<?> createDevice(@RequestBody CreateDeviceDto deviceDto) {
        return ResponseEntity.ok(deviceService.createDevice(deviceDto));
    }

}
