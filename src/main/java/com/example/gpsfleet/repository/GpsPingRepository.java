package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.GpsPing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface GpsPingRepository extends JpaRepository<GpsPing, Long> {

    List<GpsPing> findByDeviceAndSentAtBetweenOrderBySentAtAsc(
            Device deviceId,
            Instant from,
            Instant to
    );

    GpsPing findTopByDeviceOrderBySentAtDesc(Device deviceId);
}
