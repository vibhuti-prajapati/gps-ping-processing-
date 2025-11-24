package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
//CREATE TABLE trip (
//        trip_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        vehicle_id BIGINT NOT NULL,
//        driver_id BIGINT NULL,
//        deviceId VARCHAR(64) NOT NULL,
//start_time TIMESTAMP NOT NULL,
//end_time TIMESTAMP NULL,
//start_lat DECIMAL(10,7),
//start_lon DECIMAL(10,7),
//end_lat DECIMAL(10,7),
//end_lon DECIMAL(10,7),
//total_distance_m BIGINT DEFAULT 0,
//total_duration_s BIGINT DEFAULT 0,
//avg_speed_kmh DECIMAL(6,2),
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//
//CONSTRAINT fk_trip_vehicle FOREIGN KEY (vehicle_id)
//REFERENCES vehicle(vehicle_id),
//CONSTRAINT fk_trip_driver FOREIGN KEY (driver_id)
//REFERENCES driver(driver_id),
//CONSTRAINT fk_trip_device FOREIGN KEY (deviceId)
//REFERENCES device(deviceId)
//);
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long tripId;

    @ManyToOne
    @JoinColumn(name= "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name= "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name= "device_id")
    private Device device;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "start_lat")
    private Double startLat;

    @Column(name = "start_lon")
    private Double startLon;

    @Column(name = "end_lat")
    private Double endLat;

    @Column(name = "end_lon")
    private Double endLon;

    @Column(name = "total_distance_m")
    private Double totalDistanceM;

    @Column(name = "total_duration_s")
    private Long totalDurationS = 0L;

    @Column(name = "avg_speed_kmh")
    private Double avgSpeedKmh;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}

