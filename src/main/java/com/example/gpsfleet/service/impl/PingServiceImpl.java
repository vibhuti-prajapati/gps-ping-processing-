package com.example.gpsfleet.service.impl;

import com.example.gpsfleet.dto.request.PingRequestDto;
import com.example.gpsfleet.dto.response.PingResponseDto;
import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.GpsPing;
import com.example.gpsfleet.repository.DeviceRepository;
import com.example.gpsfleet.repository.GpsPingRepository;
import com.example.gpsfleet.service.PingService;
import com.example.gpsfleet.processor.TripProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PingServiceImpl implements PingService {


    private final DeviceRepository deviceRepository;
    private final GpsPingRepository gpsPingRepository;
    private final TripProcessor tripProcessor;


    @Autowired
    public PingServiceImpl(DeviceRepository deviceRepository,
                           GpsPingRepository gpsPingRepository,
                           TripProcessor tripProcessor) {
        this.deviceRepository = deviceRepository;
        this.gpsPingRepository = gpsPingRepository;
        this.tripProcessor = tripProcessor;
    }


    @Override
    @Transactional
    public PingResponseDto ingestPing(PingRequestDto dto) {
        Optional<Device> deviceOpt = deviceRepository.findById(dto.deviceId());
        if (deviceOpt.isEmpty()) {
            throw new IllegalArgumentException("Unknown deviceId: " + dto.deviceId());
        }

        GpsPing ping = new GpsPing();
        ping.setDevice(deviceOpt.get());
        ping.setLat(dto.lat());
        ping.setLon(dto.lon());
        ping.setSpeedKmh(dto.speedKmh());
        ping.setHeading(dto.heading());
        ping.setSentAt(dto.sentAt());

//  processing
        tripProcessor.enqueue(ping);
        return new PingResponseDto(ping.getPingId(), ping.getReceivedAt());
    }
}