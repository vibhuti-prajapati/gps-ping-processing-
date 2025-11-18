package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.response.VehicleLocationDto;

public interface VehicleService {
    VehicleLocationDto getLastLocation(Long vehicleId);
}
