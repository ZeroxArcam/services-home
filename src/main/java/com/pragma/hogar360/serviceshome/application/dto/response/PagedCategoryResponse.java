package com.pragma.hogar360.serviceshome.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record PagedCategoryResponse(
        @JsonProperty("categories")
        @Schema(description = "List of categories", example = "[{\"id\":1, \"name\":\"Apartamento\", \"description\":\"Vivienda ubicada en un edificio de varias unidades residenciales.\"}]")
        List<CategoryResponse> categories,

        @Schema(description = "Total number of elements", example = "21")
        long totalElements,

        @Schema(description = "Total number of pages", example = "11")
        int totalPages,

        @Schema(description = "Current page number", example = "0")
        int pageNumber,

        @Schema(description = "Number of elements per page", example = "2")
        int pageSize
) {}
