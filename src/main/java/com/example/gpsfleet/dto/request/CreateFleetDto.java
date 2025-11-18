package com.example.gpsfleet.dto.request;

public record CreateFleetDto(
        String name
){
    @Override
    public String name() {
        return name;
    }
}
