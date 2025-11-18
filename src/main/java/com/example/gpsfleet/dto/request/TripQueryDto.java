package com.example.gpsfleet.dto.request;

import java.time.Instant;

public record TripQueryDto(
        Instant from,
        Instant to
) {
    @Override
    public Instant from() {
        return from;
    }

    @Override
    public Instant to() {
        return to;
    }

    public TripQueryDto(Instant from, Instant to) {
        this.from = from;
        this.to = to;
    }
}
