package com.pragma.hogar360.serviceshome.application.dto.response;

import java.time.LocalDate;

public record HomeResponse(Long id,
                           String name,
                           String neighborhood,
                           String address,
                           String description,
                           String category,
                           Integer numberOfRooms,
                           Integer numberOfBathrooms,
                           Double price,
                           String cityName,
                           String departmentName,
                           LocalDate activePublicationDate,
                           Long userId
                           ){}