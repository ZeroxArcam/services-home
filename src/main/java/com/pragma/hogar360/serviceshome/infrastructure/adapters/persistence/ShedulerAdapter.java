package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.domain.usecases.HomeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShedulerAdapter {

    private final HomeUseCase homeUseCase;

    @Scheduled(cron = "0 */5 * * * *")
    public void dailyPublicationActivation() {
        homeUseCase.activateScheduledPublications();
    }
}
