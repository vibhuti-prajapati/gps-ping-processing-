package com.example.gpsfleet.dto.response;

import java.time.Instant;
import java.util.List;

public record TripDetailDto(
        Long tripId,
        Long vehicleId,
        Long driverId,
        Instant startTime,
        Instant endTime,
        double startLat,
        double startLon,
        double endLat,
        double endLon,
        long totalDistanceM,
        long totalDurationS,
        Double avgSpeedKmh,
        List<TripEventDto> events
) {}
