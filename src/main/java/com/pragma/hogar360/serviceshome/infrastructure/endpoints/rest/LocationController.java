package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;
import com.pragma.hogar360.serviceshome.application.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
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
 * @version 1.0
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
}
