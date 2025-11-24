package com.example.gpsfleet.dto.response;

public record AlertDriverDto(
        String alertType,
        String message

) {
    @Override
    public String alertType() {
        return alertType;
    }

    @Override
    public String message() {
        return message;
    }
}
