package com.example.gpsfleet.service;

import com.example.gpsfleet.dto.response.TripDetailDto;
import com.example.gpsfleet.dto.response.TripSummaryDto;

import java.time.Instant;
import java.util.List;

public interface TripService {
    List<TripSummaryDto> getTripsForVehicle(Long vehicleId, Instant from, Instant to);
    TripDetailDto getTripDetail(Long tripId);
}
