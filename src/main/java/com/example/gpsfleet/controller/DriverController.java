package com.example.gpsfleet.controller;

import com.example.gpsfleet.dto.request.CreateDriverDto;
import com.example.gpsfleet.service.impl.DriverServiceImpl;
import com.example.gpsfleet.service.impl.VehicleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gps/driver")
public class DriverController {
    DriverServiceImpl driverService;
    public DriverController(DriverServiceImpl driverService) {
        this.driverService = driverService;
    }
    @PostMapping("/create-driver")
    public ResponseEntity<?> createDriver(@RequestBody CreateDriverDto driverDto) {
        return ResponseEntity.ok(driverService.createDriver(driverDto));
    }
}
