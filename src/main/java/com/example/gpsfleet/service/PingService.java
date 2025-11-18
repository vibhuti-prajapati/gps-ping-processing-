package com.example.gpsfleet.service;


import com.example.gpsfleet.dto.request.PingRequestDto;
import com.example.gpsfleet.dto.response.PingResponseDto;

import java.util.List;
import java.time.Instant;


public interface PingService {
    PingResponseDto ingestPing(PingRequestDto ping);
}


