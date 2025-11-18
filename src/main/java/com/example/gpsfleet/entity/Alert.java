package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
//CREATE TABLE alert (
//        alert_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        vehicle_id BIGINT NOT NULL,
//        ping_id BIGINT NULL,
//        alert_type VARCHAR(64) NOT NULL, -- OVERSPEED, DEVIATION, DEVICE_OFFLINE
//message VARCHAR(512),
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//handled BOOLEAN DEFAULT FALSE,
//
//CONSTRAINT fk_alert_vehicle FOREIGN KEY (vehicle_id)
//REFERENCES vehicle(vehicle_id),
//CONSTRAINT fk_alert_ping FOREIGN KEY (ping_id)
//REFERENCES gps_ping(ping_id)
//);
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name="ping_id")
    private GpsPing ping;

    @Column(nullable = false)
    private String alertType; // OVERSPEED, DEVIATION, etc.

    private String message;

    private Instant createdAt = Instant.now();

    private Boolean handled = false;

}