package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Fleet;
import com.example.gpsfleet.entity.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoFenceRepository extends JpaRepository<Geofence,Long> {

    List<Geofence> findByFleet(Fleet fleet);
}
