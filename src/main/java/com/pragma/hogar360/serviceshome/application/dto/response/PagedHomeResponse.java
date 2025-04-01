package com.pragma.hogar360.serviceshome.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record PagedHomeResponse(
        @JsonProperty("home")
        @Schema(
                description = "Paged list of homes",
                example = """
                [
                    {
                        "id": 1,
                        "neighborhood": "Centro",
                        "address": "Calle 123",
                        "description": "Hermosa casa",
                        "category": "Apartamento",
                        "numberOfRooms": 3,
                        "numberOfBathrooms": 2,
                        "price": 250000.0,
                        "cityName": "Valledupar",
                        "departmentName": "Cesar",
                        "activePublicationDate": "2024-10-26"
                    },
                    {
                        "id": 2,
                        "neighborhood": "La Paz",
                        "address": "Avenida 456",
                        "description": "Casa de campo",
                        "category": "Casa",
                        "numberOfRooms": 4,
                        "numberOfBathrooms": 3,
                        "price": 350000.0,
                        "cityName": "San Alberto",
                        "departmentName": "Cesar",
                        "activePublicationDate": "2024-11-20"
                    }
                ]"""
        )
        List<HomeResponse> homes,

        @Schema(
                description = "Total elements in all pages",
                example = "21"
        )
        long totalElements,

        @Schema(
                description = "Total available pages",
                example = "11"
        )
        int totalPages,

        @Schema(
                description = "Current page (base 0)",
                example = "0"
        )
        int pageNumber,

        @Schema(
                description = "Total elements per page",
                example = "2"
        )
        int pageSize
) {}