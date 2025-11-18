package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record AlertDto(
        Long alertId,
        Long vehicleId,
        Long pingId,
        String alertType,
        String message,
        Instant createdAt,
        boolean handled
) {
    @Override
    public Long alertId() {
        return alertId;
    }

    @Override
    public Long vehicleId() {
        return vehicleId;
    }

    @Override
    public Long pingId() {
        return pingId;
    }

    @Override
    public String alertType() {
        return alertType;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public Instant createdAt() {
        return createdAt;
    }

    @Override
    public boolean handled() {
        return handled;
    }
}
