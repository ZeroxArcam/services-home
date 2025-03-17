package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;

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
     * Retrieves a paginated list of categories with metadata.
     *
     * @param page      The page number (0-based).
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A CategoryPageResponse DTO containing the list of categories and pagination metadata.
     */
    PagedCategoryResponse getCategories(Integer page, Integer size, boolean orderAsc);

    /**
     * Retrieves a CategoryResponse by its name.
     *
     * @param name The name of the category to retrieve.
     * @return The CategoryResponse object if found, or null if not found.
     */
    CategoryResponse getCategoryByName(String name);
}