package com.pragma.hogar360.serviceshome.infrastructure.adapters.scheduler;

import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShedulerAdapter {

    private final HomeServicePort homeServicePort;

    @Scheduled(cron = "0 */5 * * * *")
    public void dailyPublicationActivation() {
        homeServicePort.activateScheduledPublications();
    }
}