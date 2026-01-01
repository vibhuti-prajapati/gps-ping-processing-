package com.example.gpsfleet.dto.request;

import com.example.gpsfleet.entity.GpsPing;
import com.example.gpsfleet.entity.Status;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceState {
    private GpsPing lastPing;
    private boolean inTrip;
    private Long currentTripId;
    private Instant lastMovementTime;
    private boolean isIdle;
    private Instant idleStartTime;
    private Double lastLat;
    private Double lastLon;
    private Long currentIdleEventId;
    private int movementCounter;
    private boolean insideGeofence;
    private Status status=Status.ONLINE;
    private double  distanceCovered;
}