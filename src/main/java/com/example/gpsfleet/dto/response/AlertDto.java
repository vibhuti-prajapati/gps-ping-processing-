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
) {}
