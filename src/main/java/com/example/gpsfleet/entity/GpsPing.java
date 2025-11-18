package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
//CREATE TABLE gps_ping (
//        ping_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        deviceId VARCHAR(64) NOT NULL,
//lat DECIMAL(10,7) NOT NULL,
//lon DECIMAL(10,7) NOT NULL,
//speedKmh DECIMAL(6,2),
//heading DECIMAL(6,2),
//sentAt TIMESTAMP NOT NULL,
//received_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//
//CONSTRAINT fk_ping_device FOREIGN KEY (deviceId)
//REFERENCES device(deviceId)
//);

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gps_ping")
public class GpsPing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pingId;

    @ManyToOne
    @JoinColumn(name= "device_id")
    private Device device;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lon;

    @Column(name= "speed_kmh")
    private Double speedKmh;

    private Double heading;

    @Column(nullable = false)
    private Instant sentAt;

    private Instant receivedAt = Instant.now();

    private Instant createdAt = Instant.now();
}
