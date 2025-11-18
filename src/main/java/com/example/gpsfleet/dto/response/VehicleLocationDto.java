package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record VehicleLocationDto(
        Long vehicleId,
        double lat,
        double lon,
        Double speedKmh,
        Instant lastUpdate
) {
    @Override
    public Long vehicleId() {
        return vehicleId;
    }

    @Override
    public double lat() {
        return lat;
    }

    @Override
    public double lon() {
        return lon;
    }

    @Override
    public Double speedKmh() {
        return speedKmh;
    }

    @Override
    public Instant lastUpdate() {
        return lastUpdate;
    }
}
