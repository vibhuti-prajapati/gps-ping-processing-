package com.example.gpsfleet.controller;

import com.example.gpsfleet.dto.request.CreateFleetDto;
import com.example.gpsfleet.service.impl.FleetServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gps")
public class FleetController {
    FleetServiceImpl fleetService;
    public FleetController(FleetServiceImpl fleetService) {
        this.fleetService = fleetService;
    }

    @GetMapping("/")
    public String getFleet() {
        System.out.println("fet fleet");

        return "fleet is here";
    }

    @PostMapping("/create-fleet")
    public ResponseEntity<?> createFleet(@RequestBody CreateFleetDto fleet) {
        System.out.println("createFleet");
        return ResponseEntity.ok(fleetService.createFleet(fleet));
    }
}
