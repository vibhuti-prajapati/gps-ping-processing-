package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record PingResponseDto(
        Long pingId,
        Instant receivedAt
) {}
