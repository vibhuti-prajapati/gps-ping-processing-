package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.Trip;
import com.example.gpsfleet.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByVehicleAndStartTimeBetweenOrderByStartTimeAsc(
            Vehicle vehicle,
            Instant from,
            Instant to
    );

    Trip findTopByDeviceOrderByStartTimeDesc(Device device);
}
