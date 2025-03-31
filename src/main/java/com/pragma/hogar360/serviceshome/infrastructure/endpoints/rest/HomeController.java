package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveHomeResponse;
import com.pragma.hogar360.serviceshome.application.services.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}