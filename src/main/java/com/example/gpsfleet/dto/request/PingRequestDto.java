package com.example.gpsfleet.dto.request;

import com.example.gpsfleet.entity.Device;

import java.time.Instant;

public record PingRequestDto(
        String deviceId,
        double lat,
        double lon,
        Double speedKmh,
        Double heading,
        Instant sentAt
) {
    @Override
    public String deviceId() {
        return deviceId;
    }

    @Override
    public double lat() {
        return lat;
    }

    @Override
    public double lon() {
        return lon;
    }

    @Override
    public Double speedKmh() {
        return speedKmh;
    }

    @Override
    public Double heading() {
        return heading;
    }

    @Override
    public Instant sentAt() {
        return sentAt;
    }



    public PingRequestDto(String deviceId, double lat, double lon, Double speedKmh, Double heading, Instant sentAt) {
        this.deviceId = deviceId;
        this.lat = lat;
        this.lon = lon;
        this.speedKmh = speedKmh;
        this.heading = heading;
        this.sentAt = sentAt;
    }
}
