package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.request.CreateDriverDto;

public interface DriverService {
    // minimal for now
    String createDriver(CreateDriverDto driverDto);
    boolean existsById(Long driverId);
}
