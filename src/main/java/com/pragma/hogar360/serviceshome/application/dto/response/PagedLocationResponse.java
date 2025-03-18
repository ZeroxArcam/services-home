package com.pragma.hogar360.serviceshome.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record PagedLocationResponse(
        @JsonProperty("locations")
        @Schema(description = "List of locations", example = "[{\"id\":1, \"cityName\":\"Valledupar\", \"departmentName\":\"Cesar\"}]")
        List<LocationResponse> locations,

        @Schema(description = "Total number of elements", example = "21")
        long totalElements,

        @Schema(description = "Total number of pages", example = "11")
        int totalPages,

        @Schema(description = "Current page number", example = "0")
        int pageNumber,

        @Schema(description = "Number of elements per page", example = "2")
        int pageSize
) {}