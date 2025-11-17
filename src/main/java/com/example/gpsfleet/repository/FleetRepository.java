package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetRepository extends JpaRepository<Fleet,Long> {
}
