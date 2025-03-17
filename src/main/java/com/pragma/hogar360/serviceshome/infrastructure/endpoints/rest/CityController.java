package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCityResponse;
import com.pragma.hogar360.serviceshome.application.services.CityService;
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
 * REST controller for managing city operations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 *
 */
@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Tag(name = "Cities", description = "Operations related to cities")
public class CityController {

    private final CityService cityService;

    /**
     * Endpoint to save a new city.
     *
     * @param saveCityRequest The request containing city data.
     * @return A ResponseEntity with the save response.
     */
    @PostMapping("/")
    @Operation(summary = "Save a new city", description = "Saves a new city in the system. The city name must be unique and have a maximum length of 50 characters. Notes = Ensure that the city name is properly formatted.")
    @ApiResponse(responseCode = "201", description = "City created", content = @Content(schema = @Schema(implementation = SaveCityResponse.class), examples = @ExampleObject(value = "{\"id\": 1, \"name\": \"Valledupar\", \"description\": \"Capital of Cesar\"}")))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Name cannot exceed 50 characters.\"\n \"Description cannot exceed 90 characters.\" ")))
    @ApiResponse(responseCode = "409", description = "City already exists", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "City with name 'Valledupar' already exists.")))
    public ResponseEntity<SaveCityResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "City data to save",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\"name\": \"Valledupar\", \"description\": \"Capital of Cesar\"}"
                            )
                    )
            )
            @RequestBody SaveCityRequest saveCityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.createCity(saveCityRequest));
    }

}