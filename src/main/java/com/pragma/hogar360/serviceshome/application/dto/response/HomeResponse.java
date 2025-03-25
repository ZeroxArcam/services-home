package com.pragma.hogar360.serviceshome.application.dto.response;

import java.time.LocalDate;

public record HomeResponse(Long id,
                           String nombre,
                           String description,
                           String category,
                           Integer numberOfRooms,
                           Integer numberOfBathrooms,
                           Double price,
                           String city,
                           String department,
                           LocalDate activePublicationDate,
                           String publicationStatus,
                           String Tecnology){}
