package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByRegNo(String regNo);
}
