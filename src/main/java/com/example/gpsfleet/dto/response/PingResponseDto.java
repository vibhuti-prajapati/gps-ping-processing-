package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record PingResponseDto(
        Long pingId,
        Instant receivedAt
) {
    @Override
    public Long pingId() {
        return pingId;
    }

    @Override
    public Instant receivedAt() {
        return receivedAt;
    }
}
