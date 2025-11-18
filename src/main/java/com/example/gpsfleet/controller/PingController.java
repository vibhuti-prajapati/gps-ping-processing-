package com.example.gpsfleet.controller;

import com.example.gpsfleet.dto.request.PingRequestDto;
import com.example.gpsfleet.dto.response.PingResponseDto;
import com.example.gpsfleet.service.impl.DeviceServiceImpl;
import com.example.gpsfleet.service.impl.PingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gps")
public class PingController {
    private final PingServiceImpl pingService;
    public PingController(PingServiceImpl pingService) {
        this.pingService = pingService;
    }

    @PostMapping("/send-ping")
    public ResponseEntity<PingResponseDto> sendPing(@RequestBody PingRequestDto pingRequestDto){
        return ResponseEntity.ok(pingService.ingestPing(pingRequestDto));
    }
}
