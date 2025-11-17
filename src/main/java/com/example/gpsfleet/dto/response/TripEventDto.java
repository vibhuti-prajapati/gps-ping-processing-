package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record TripEventDto(
        Long eventId,
        String eventType,
        Instant startTime,
        Instant endTime,
        String metadata
) {}
