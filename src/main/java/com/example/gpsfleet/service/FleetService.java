package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.request.CreateFleetDto;

public interface FleetService {
    String createFleet(CreateFleetDto fleet);
    // minimal for now
    boolean existsById(Long fleetId);
}
