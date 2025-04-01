package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedHomeResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveHomeResponse;
import com.pragma.hogar360.serviceshome.application.services.HomeService;
import com.pragma.hogar360.serviceshome.domain.model.HomeQueryModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
@Tag(name = "Home", description = "Operations related to home")
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/")
    @Operation(summary = "Save a new home", description = "Saves a new home in the system.")
    @ApiResponse(responseCode = "201", description = "Home created", content = @Content(schema = @Schema(implementation = SaveHomeResponse.class), examples = @ExampleObject(value = "{\"id\": 1, \"message\": \"Home created successfully\"}")))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Invalid input data\"")))
    public ResponseEntity<SaveHomeResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Home data to save",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\"name\": \"Mi Casa\", \"address\": \"Calle 123\", \"description\": \"Una hermosa casa\", \"category\": \"Apartamento\", \"numberOfRooms\": 3, \"numberOfBathrooms\": 2, \"price\": 150000.0, \"cityId\": 1, \"activePublicationDate\": \"2025-03-25\", \"publicationDate\": \"2025-03-25\"}"
                            )
                    )
            )
            @RequestBody SaveHomeRequest saveHomeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.save(saveHomeRequest));
    }

    @GetMapping("/search")
    @Operation(summary = "Search homes", description = "Searches homes with pagination, sorting, and filtering.")
    @ApiResponse(responseCode = "200", description = "Search results", content = @Content(schema = @Schema(implementation = PagedHomeResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class)))
    public ResponseEntity<PagedHomeResponse> searchHomes(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Number of homes per page") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "Field to sort by (price, numberOfRooms, numberOfBathrooms, locationId, categoryId)") @RequestParam(defaultValue = "price") String sortBy,
            @Parameter(description = "Sorting direction (ASC or DESC)") @RequestParam(defaultValue = "ASC") String sortDirection,
            @Parameter(description = "Location ID for filtering homes") @RequestParam(required = false) Long locationId,
            @Parameter(description = "Category ID for filtering homes") @RequestParam(required = false) Long categoryId,
            @Parameter(description = "Minimum number of rooms") @RequestParam(required = false) Integer minRooms,
            @Parameter(description = "Maximum number of rooms") @RequestParam(required = false) Integer maxRooms,
            @Parameter(description = "Minimum number of bathrooms") @RequestParam(required = false) Integer minBathrooms,
            @Parameter(description = "Maximum number of bathrooms") @RequestParam(required = false) Integer maxBathrooms,
            @Parameter(description = "Minimum price") @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price") @RequestParam(required = false) BigDecimal maxPrice,
            @Parameter(description = "Current date for active publication") @RequestParam(required = false) LocalDate currentDate
    ) {
        HomeQueryModel queryModel = new HomeQueryModel();
        queryModel.setLocationId(locationId);
        queryModel.setCategoryId(categoryId);
        queryModel.setMinRooms(minRooms);
        queryModel.setMaxRooms(maxRooms);
        queryModel.setMinBathrooms(minBathrooms);
        queryModel.setMaxBathrooms(maxBathrooms);
        queryModel.setMinPrice(minPrice);
        queryModel.setMaxPrice(maxPrice);
        queryModel.setCurrentDate(currentDate);

        return ResponseEntity.ok(homeService.findHomesByFilters(queryModel, page, size, sortBy, sortDirection));
    }
}