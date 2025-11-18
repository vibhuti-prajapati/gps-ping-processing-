package com.example.gpsfleet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

//CREATE TABLE vehicle (
//        vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        fleet_id BIGINT NOT NULL,
//        driver_id BIGINT NULL,
//        reg_no VARCHAR(64) UNIQUE,
//model VARCHAR(128),
//active BOOLEAN DEFAULT TRUE,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//
//CONSTRAINT fk_vehicle_fleet FOREIGN KEY (fleet_id)
//REFERENCES fleet(fleet_id),
//CONSTRAINT fk_vehicle_driver FOREIGN KEY (driver_id)
//REFERENCES driver(driver_id)
//);
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicle_id;

    @ManyToOne
    @JoinColumn(name = "fleet_id")
    private Fleet fleetId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(unique = true)
    private String regNo;

    private String model;

    private Boolean active = true;

    private Instant createdAt = Instant.now();
}
