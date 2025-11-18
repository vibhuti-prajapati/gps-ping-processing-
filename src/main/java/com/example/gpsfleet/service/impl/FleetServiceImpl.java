package com.example.gpsfleet.service.impl;

import com.example.gpsfleet.dto.request.CreateFleetDto;
import com.example.gpsfleet.entity.Fleet;
import com.example.gpsfleet.repository.FleetRepository;
import com.example.gpsfleet.service.FleetService;
import org.springframework.stereotype.Service;

@Service
public class FleetServiceImpl implements FleetService {
    private final FleetRepository fleetRepository;

    public FleetServiceImpl(FleetRepository fleetRepository) {
        this.fleetRepository = fleetRepository;
    }
    @Override
    public String createFleet(CreateFleetDto fleetDto) {
        fleetRepository.save(new Fleet(fleetDto));
        return "Fleet created";
    }

    @Override
    public boolean existsById(Long fleetId) {
        return false;
    }
}
