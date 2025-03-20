package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveDepartmentRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.DepartmentResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveDepartmentResponse;
import com.pragma.hogar360.serviceshome.application.services.DepartmentService;
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
 * REST controller for managing department operations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 *
 */
@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Tag(name = "Departments", description = "Operations related to departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    /**
     * Endpoint to save a new department.
     *
     * @param saveDepartmentRequest The request containing department data.
     * @return A ResponseEntity with the save response.
     */
    @PostMapping("/")
    @Operation(summary = "Save a new department", description = "Saves a new department in the system. The department name must be unique. Notes = Ensure that the department name is properly formatted.")
    @ApiResponse(responseCode = "201", description = "Department created", content = @Content(schema = @Schema(implementation = DepartmentResponse.class), examples = @ExampleObject(value = "{\"id\": 1, \"name\": \"Cesar\", \"description\": \"Department in the north of Colombia\"}")))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "\"Name cannot exceed 50 characters.\"\n \"Description cannot exceed 90 characters.\" ")))
    @ApiResponse(responseCode = "409", description = "Department already exists", content = @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "Department with name 'Cesar' already exists.")))
    public ResponseEntity<SaveDepartmentResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Department data to save",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\"name\": \"Cesar\", \"description\": \"Department in the north of Colombia\"}"
                            )
                    )
            )
            @RequestBody SaveDepartmentRequest saveDepartmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(saveDepartmentRequest));
    }
}