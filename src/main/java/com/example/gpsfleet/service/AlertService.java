package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.response.AlertDto;

import java.util.List;

public interface AlertService {
    List<AlertDto> getAlertsForVehicle(Long vehicleId);
    void acknowledgeAlert(Long alertId);
}
