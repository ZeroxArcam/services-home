package com.pragma.hogar360.serviceshome.application.dto.request;

import java.time.LocalDate;

public record SaveHomeRequest(String name,
                              String description,
                              String category,
                              Integer numberOfRooms,
                              Integer numberOfBathrooms,
                              Double price,
                              String city,
                              String department,
                              LocalDate activePublicationDate,
                              String publicationStatus,
                              LocalDate publicationDate) {}
