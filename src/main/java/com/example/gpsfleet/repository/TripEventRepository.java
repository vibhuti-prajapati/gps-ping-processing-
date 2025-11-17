package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Trip;
import com.example.gpsfleet.entity.TripEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripEventRepository extends JpaRepository<TripEvent, Long> {

    List<TripEvent> findByTripId(Trip trip);
}
