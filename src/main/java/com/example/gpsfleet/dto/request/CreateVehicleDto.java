package com.example.gpsfleet.dto.request;

import com.example.gpsfleet.entity.Driver;
import com.example.gpsfleet.entity.Fleet;

public record CreateVehicleDto(
        long fleetId,
        long driverId,
        String regNo,
        String model
) {
}
