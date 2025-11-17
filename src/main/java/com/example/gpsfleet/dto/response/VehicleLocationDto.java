package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record VehicleLocationDto(
        Long vehicleId,
        double lat,
        double lon,
        Double speedKmh,
        Instant lastUpdate
) {}
