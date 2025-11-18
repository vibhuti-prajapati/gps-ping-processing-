package com.example.gpsfleet.dto.request;

import lombok.*;

public record AlertAcknowledgeDto(
        Long alertId
) {
    @Override
    public Long alertId() {
        return alertId;
    }

    public AlertAcknowledgeDto(Long alertId) {
        this.alertId = alertId;
    }
}
