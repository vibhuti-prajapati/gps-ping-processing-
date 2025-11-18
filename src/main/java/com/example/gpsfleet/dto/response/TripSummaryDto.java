package com.example.gpsfleet.dto.response;

import java.time.Instant;

public record TripSummaryDto(
        Long tripId,
        Instant startTime,
        Instant endTime,
        long totalDistanceM,
        long totalDurationS,
        Double avgSpeedKmh
) {
    @Override
    public Long tripId() {
        return tripId;
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
    public long totalDistanceM() {
        return totalDistanceM;
    }

    @Override
    public long totalDurationS() {
        return totalDurationS;
    }

    @Override
    public Double avgSpeedKmh() {
        return avgSpeedKmh;
    }
}
