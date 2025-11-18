package com.example.gpsfleet.dto.request;

public record CreateDeviceDto (
        String deviceId,
        String imei,
        long vehicleId
){
    @Override
    public String deviceId() {
        return deviceId;
    }

    @Override
    public String imei() {
        return imei;
    }

    @Override
    public long vehicleId() {
        return vehicleId;
    }

}
