package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Alert;
import com.example.gpsfleet.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByVehicleOrderByCreatedAtDesc(Vehicle vehicleId);
}
