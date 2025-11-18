package com.example.gpsfleet.dto.request;

public record CreateDriverDto(
        String name,
        String phone,
        String licenseNo
) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String licenseNo() {
        return licenseNo;
    }
}
