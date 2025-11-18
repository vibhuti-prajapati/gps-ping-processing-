package com.example.gpsfleet.entity;

import com.example.gpsfleet.dto.request.CreateFleetDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

//CREATE TABLE fleet (
//        fleet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        name VARCHAR(128) NOT NULL,
//owner_id BIGINT,
//created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="fleet")
public class Fleet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long fleetId;

    @Column(name="name")
    private String name;

//    @Column(name="owner_id")
//    private long ownerId;

    @Column(name="created_at")
    private Instant createdAt= Instant.now();

    public Fleet(CreateFleetDto dto) {
        this.name = dto.name();
    }

}
