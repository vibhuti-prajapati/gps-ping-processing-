package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record TripEventDto(
        Long eventId,
        String eventType,
        Instant startTime,
        Instant endTime,
        String metadata
) {
    @Override
    public Long eventId() {
        return eventId;
    }

    @Override
    public String eventType() {
        return eventType;
    }

    @Override
    public Instant startTime() {
        return startTime;
    }

    @Override
    public Instant endTime() {
        return endTime;
    }

    @Override
    public String metadata() {
        return metadata;
    }
}
