package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
}
