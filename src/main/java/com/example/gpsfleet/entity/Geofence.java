package com.example.gpsfleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Geofence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fleetId")
    private Fleet fleet;
    private String name;
    private double centerLat;
    private double centerLon;
    private double radiusMeters;

    private Instant createdAt = Instant.now();
}