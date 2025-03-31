package com.pragma.hogar360.serviceshome.application.dto.request;

import java.time.LocalDate;

public record SaveHomeRequest(String name,
                              String address,
                              String description,
                              String category,
                              Integer numberOfRooms,
                              Integer numberOfBathrooms,
                              Double price,
                              Long cityId,
                              LocalDate activePublicationDate,
                              //String publicationStatus,
                              LocalDate publicationDate) {}