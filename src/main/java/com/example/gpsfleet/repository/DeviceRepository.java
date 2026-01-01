package com.example.gpsfleet.repository;

import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,String> {
    Optional<Device> findByImei(String Imei);

    Device findDeviceByDeviceId(String deviceId);

    Optional<Device> findByVehicle(Vehicle vehicle);
}
