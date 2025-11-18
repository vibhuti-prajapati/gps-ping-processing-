package com.example.gpsfleet.entity;
import jakarta.persistence.*;

import java.time.Instant;
//CREATE TABLE device (
//        deviceId VARCHAR(64) PRIMARY KEY,
//imei VARCHAR(64) UNIQUE,
//vehicle_id BIGINT NOT NULL,
//active BOOLEAN DEFAULT TRUE,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//
//CONSTRAINT fk_device_vehicle FOREIGN KEY (vehicle_id)
//REFERENCES vehicle(vehicle_id)
//);

@Entity
@Table(name= "device")
public class Device {

    @Id
    @Column(length = 64)
    private String deviceId;

    private String imei;

    @ManyToOne
    @JoinColumn(name= "vehicle_id")
    private Vehicle vehicle;

    private Boolean active;

    private Instant createdAt=Instant.now();
}
