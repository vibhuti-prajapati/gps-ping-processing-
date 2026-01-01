package com.example.gpsfleet.dto.response;

import com.example.gpsfleet.entity.Status;

import java.time.Instant;

public record VehicleLocationDto(
        long vehicleId,
        double lastLat,
        double lastLon,
        String lastKnownLocation,
        Instant lastMovement,
        boolean isInTrip,
        Status status
) {
    @Override
    public long vehicleId() {
        return vehicleId;
    }

    @Override
    public double lastLat() {
        return lastLat;
    }

    @Override
    public double lastLon() {
        return lastLon;
    }

    @Override
    public String lastKnownLocation() {
        return lastKnownLocation;
    }

    @Override
    public Instant lastMovement() {
        return lastMovement;
    }

    @Override
    public boolean isInTrip() {
        return isInTrip;
    }

    @Override
    public Status status() {
        return status;
    }
}
