package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device> findByImei(String Imei);
}
