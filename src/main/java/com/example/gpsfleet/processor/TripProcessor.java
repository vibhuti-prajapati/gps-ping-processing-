package com.example.gpsfleet.processor;

import com.example.gpsfleet.entity.GpsPing;

public interface TripProcessor {
    void enqueue(GpsPing ping);
    void start();
    void stop();
}