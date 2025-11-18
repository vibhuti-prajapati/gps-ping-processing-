package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
