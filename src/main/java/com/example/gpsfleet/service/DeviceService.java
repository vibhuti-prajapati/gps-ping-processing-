package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.request.CreateDeviceDto;

public interface DeviceService {
    String createDevice(CreateDeviceDto dto);
    boolean existsById(String deviceId);
}
