package com.example.gpsfleet.dto.request;

public record VehicleRegisterDto(
        Long fleetId,
        String regNo,
        String model,
        Long driverId
) {
    @Override
    public Long fleetId() {
        return fleetId;
    }

    @Override
    public String regNo() {
        return regNo;
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public Long driverId() {
        return driverId;
    }

    public VehicleRegisterDto(Long fleetId, String regNo, String model, Long driverId) {
        this.fleetId = fleetId;
        this.regNo = regNo;
        this.model = model;
        this.driverId = driverId;
    }
}
