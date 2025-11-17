package com.example.gpsfleet.dto.request;

import java.time.Instant;

public record PingRequestDto(
        String device_id,
        double lat,
        double lon,
        Double speed_kmh,
        Double heading,
        Instant sent_at
) {}
