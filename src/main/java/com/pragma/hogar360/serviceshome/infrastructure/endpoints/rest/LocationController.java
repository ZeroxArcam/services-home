package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;
import com.pragma.hogar360.serviceshome.application.services.LocationService;
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

/**
 * REST controller for managing location operations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
 * @since [16/3/2025]
 *
 */
@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
@Tag(name = "Locations", description = "Operations related to locations")
public class LocationController {

    private final LocationService locationService;

    /**
     * Endpoint to save a new location.
     *
     * @param saveLocationRequest The request containing location data.
     * @return A ResponseEntity with the save response.
     */
    @PostMapping("/")
    @Operation(summary = "Save a new location", description = "Saves a new location in the system. The city and department must exist. The combination of city and department must be unique. Notes = Ensure that the city and department names are properly formatted and exist in the system.")
    @ApiResponse(responseCode = "201", description = "Location created", content = @Content(schema = @Schema(implementation = SaveLocationResponse.class), examples = @ExampleObject(value = "{\"id\": 1, \"cityName\": \"Valledupar\", \"departmentName\": \"Cesar\"}")))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Name cannot exceed 50 characters.\"\n \"Description cannot exceed 90 characters.\" ")))
    @ApiResponse(responseCode = "404", description = "City or department not found", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "City 'Valledupar' not found.")))
    @ApiResponse(responseCode = "409", description = "Location already exists", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "Location 'Valledupar, Cesar' already exists.")))
    public ResponseEntity<SaveLocationResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Location data to save",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\"cityName\": \"Valledupar\", \"departmentName\": \"Cesar\"}"
                            )
                    )
            )
            @RequestBody SaveLocationRequest saveLocationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.createLocation(saveLocationRequest));
    }

    /**
     * Endpoint to search locations with pagination and sorting.
     *
     * @param page          The page number (0-based).
     * @param size          The number of locations per page.
     * @param sortBy        The field to sort by ("cityName" or "departmentName").
     * @param sortDirection The sorting direction ("ASC" or "DESC").
     * @param text          The search text for filtering locations.
     * @return A ResponseEntity with the paged location response.
     */
    @GetMapping("/search")
    @Operation(summary = "Search locations", description = "Searches locations with pagination and sorting.")
    @ApiResponse(responseCode = "200", description = "Search results", content = @Content(schema = @Schema(implementation = PagedLocationResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class)))
    public ResponseEntity<PagedLocationResponse> search(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Number of locations per page") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "Field to sort by (cityName or departmentName)") @RequestParam(defaultValue = "cityName") String sortBy,
            @Parameter(description = "Sorting direction (ASC or DESC)") @RequestParam(defaultValue = "ASC") String sortDirection,
            @Parameter(description = "Search text for filtering locations") @RequestParam(defaultValue = "") String text) {

        return ResponseEntity.ok(locationService.getLocations(page, size, sortBy, sortDirection, text));
    }
}