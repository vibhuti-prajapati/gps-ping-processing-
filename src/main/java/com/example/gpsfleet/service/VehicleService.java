package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.request.CreateVehicleDto;
import com.example.gpsfleet.dto.response.VehicleLocationDto;

public interface VehicleService {
    String createVehicle(CreateVehicleDto vehicle);
    boolean existsById(Long fleetId);
    VehicleLocationDto getLastLocation(Long vehicleId);
}
