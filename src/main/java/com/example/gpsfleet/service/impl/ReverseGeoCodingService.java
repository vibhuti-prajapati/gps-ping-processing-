package com.example.gpsfleet.service.impl;

import jdk.jfr.RecordingState;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class ReverseGeoCodingService {
    private final RestTemplate restTemplate =  new RestTemplate();

    public String getAddress(double lat, double lon) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/reverse")
                .queryParam("lat",lat)
                .queryParam("lon",lon)
                .queryParam("format","json")
                .queryParam("addressdetails",1)
                .toUriString();
        Map response = restTemplate.getForObject(url, Map.class);
        if(response==null) return null;
        Object displayName = response.get("display_name");
        return displayName!=null ? displayName.toString():null;
    }
}
