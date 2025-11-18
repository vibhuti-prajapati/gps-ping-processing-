package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

//CREATE TABLE driver (
//        driver_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        name VARCHAR(128) NOT NULL,
//phone VARCHAR(32),
//license_no VARCHAR(64),
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;

    @Column(name= "name")
    private String name;

    @Column(name= "phone")
    private String phone;

    @Column(name= "license_no")
    private String licenseNo;

    @Column(name= "created_at")
    private Instant createdAt=Instant.now();

}
