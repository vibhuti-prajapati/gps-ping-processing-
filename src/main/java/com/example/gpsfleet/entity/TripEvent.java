package com.example.gpsfleet.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

//CREATE TABLE trip_event (
//        event_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        trip_id BIGINT NOT NULL,
//        event_type VARCHAR(64) NOT NULL, -- STOP, OVERSPEED, DEVIATION, IDLE
//start_time TIMESTAMP NOT NULL,
//end_time TIMESTAMP NULL,
//metadata JSON,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//
//CONSTRAINT fk_event_trip FOREIGN KEY (trip_id)
//REFERENCES trip(trip_id)
//);
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="trip_event")
public class TripEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip tripId;
    @Column(nullable = false)
    private String eventType; // STOP, OVERSPEED, DEVIATION, IDLE

    @Column(nullable = false)
    private Instant startTime;

    private Instant endTime;

    @Column(columnDefinition = "JSON")
    private String metadata;

    private Instant createdAt = Instant.now();
}
