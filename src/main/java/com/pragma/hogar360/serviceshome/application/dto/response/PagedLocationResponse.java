package com.pragma.hogar360.serviceshome.application.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
public record PagedLocationResponse(
        @JsonProperty("locations")
        @Schema(
                description = "Paged list locations",
                example = """
                [
                    {
                        "id": 1,
                        "cityName": "Valledupar",
                        "departmentName": "Cesar",
                        "neighborhood": "Centro"
                    },
                    {
                        "id": 2,
                        "cityName": "San Alberto",
                        "departmentName": "Cesar",
                        "neighborhood": "La Paz"
                    }
                ]"""
        )
        List<LocationResponse> locations,
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
                description = "Current page(base 0)",
                example = "0"
        )
        int pageNumber,

        @Schema(
                description = "Total elements per page",
                example = "2"
        )
        int pageSize
) {}