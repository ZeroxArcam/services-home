package com.pragma.hogar360.serviceshome.infrastructure.endpoints.rest;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.services.CategoryService;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing category operations.
 */
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Operations related to categories")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Endpoint to save a new category.
     *
     * @param saveCategoryRequest The request containing category data.
     * @return A ResponseEntity with the save response.
     */
    @PostMapping("/")
    @Operation(summary = "Save a new category", description = "Saves a new category in the system.")
    @ApiResponse(responseCode = "201", description = "Category created", content = @Content(schema = @Schema(implementation = SaveCategoryResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class)))
    public ResponseEntity<SaveCategoryResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Category data to save", required = true)
            @RequestBody SaveCategoryRequest saveCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(saveCategoryRequest));
    }

    /**
     * Endpoint to retrieve all categories with pagination.
     *
     * @param page      The page number.
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A ResponseEntity with the list of categories.
     */
    @GetMapping("/")
    @Operation(summary = "Get all categories paginated", description = "Retrieves a list of all categories with pagination.")
    @ApiResponse(responseCode = "200", description = "List of categories", content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    public ResponseEntity<List<CategoryResponse>> getAllCategories(
            @Parameter(description = "Page number", required = true) @RequestParam Integer page,
            @Parameter(description = "Page size", required = true) @RequestParam Integer size,
            @Parameter(description = "Ascending order (true) or descending (false)", required = true) @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(categoryService.getCategories(page, size, orderAsc));
    }

}