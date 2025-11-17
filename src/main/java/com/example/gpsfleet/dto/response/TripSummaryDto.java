package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record TripSummaryDto(
        Long tripId,
        Instant startTime,
        Instant endTime,
        long totalDistanceM,
        long totalDurationS,
        Double avgSpeedKmh
) {}
