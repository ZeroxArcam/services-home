package com.pragma.hogar360.serviceshome.application.dto.response;

import java.time.LocalDate;

public record HomeResponse(Long id,
                           String name,
                           String address,
                           String description,
                           String category,
                           Integer numberOfRooms,
                           Integer numberOfBathrooms,
                           Double price,
                           Long cityId,
                           LocalDate activePublicationDate,
                           String publicationStatus,
                           String Tecnology){}