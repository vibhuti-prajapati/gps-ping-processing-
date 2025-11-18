package com.example.gpsfleet.controller;

import com.example.gpsfleet.dto.request.CreateVehicleDto;
import com.example.gpsfleet.service.impl.FleetServiceImpl;
import com.example.gpsfleet.service.impl.VehicleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gps/vehicle")
public class VehicleController {
    VehicleServiceImpl vehicleService;
    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create-vehicle")
    public ResponseEntity<?> createVehicle(@RequestBody CreateVehicleDto vehicleDto) {
        System.out.println(vehicleDto.fleetId()+vehicleDto.model());
    return  ResponseEntity.ok(vehicleService.createVehicle(vehicleDto));
    }
}
