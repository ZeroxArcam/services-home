package com.pragma.hogar360.serviceshome.domain.ports.in;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;



/**
 * Port interface for category service operations.
 * Defines the contract for interacting with the category service layer.
 */
public interface CategoryServicePort {

    /**
     * Saves a CategoryModel.
     *
     * @param categoryModel The CategoryModel to be saved.
     */
    void save(CategoryModel categoryModel);

    /**
     * Retrieves a paginated list of CategoryModels.
     *
     * @param page      The page number (0-based).
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A list of CategoryModels representing the requested page.
     */
    Pagination<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);

    /**
     * Retrieves a CategoryModel by its name.
     *
     * @param name The name of the category to retrieve.
     * @return The CategoryModel object if found, or null if not found.
     */
    CategoryModel getCategoryByName(String name);
}