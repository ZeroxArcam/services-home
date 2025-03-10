package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;

import java.util.List;

/**
 * Service interface for category operations.
 * Defines the contract for category-related business logic.
 */
public interface CategoryService {

    /**
     * Saves a new category based on the provided request.
     *
     * @param request The SaveCategoryRequest containing the category data.
     * @return A SaveCategoryResponse containing the result of the save operation.
     */
    SaveCategoryResponse save(SaveCategoryRequest request);

    /**
     * Retrieves a paginated list of categories.
     *
     * @param page      The page number (0-based).
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A list of CategoryResponse DTOs representing the requested page.
     */
    List<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);
    CategoryResponse getCategoryByName(String name);

}