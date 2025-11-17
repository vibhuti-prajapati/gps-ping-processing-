package com.example.gpsfleet.dto.request;

import java.time.Instant;

public record TripQueryDto(
        Instant from,
        Instant to
) {}
