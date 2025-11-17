package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import java.time.Instant;
//CREATE TABLE gps_ping (
//        ping_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        device_id VARCHAR(64) NOT NULL,
//lat DECIMAL(10,7) NOT NULL,
//lon DECIMAL(10,7) NOT NULL,
//speed_kmh DECIMAL(6,2),
//heading DECIMAL(6,2),
//sent_at TIMESTAMP NOT NULL,
//received_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//
//CONSTRAINT fk_ping_device FOREIGN KEY (device_id)
//REFERENCES device(device_id)
//);

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
