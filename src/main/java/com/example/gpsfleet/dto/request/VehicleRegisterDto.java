package com.example.gpsfleet.dto.request;

public record VehicleRegisterDto(
        Long fleetId,
        String regNo,
        String model,
        Long driverId
) {}
