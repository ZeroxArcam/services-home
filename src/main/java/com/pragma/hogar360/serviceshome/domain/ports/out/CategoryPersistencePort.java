package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import java.util.List;

/**
 * Port interface for category persistence operations.
 * Defines the contract for interacting with the data persistence layer for CategoryModel.
 */
public interface CategoryPersistencePort {

    /**
     * Saves a CategoryModel to the persistence layer.
     *
     * @param categoryModel The CategoryModel to be saved.
     */
    void save(CategoryModel categoryModel);

    /**
     * Retrieves a CategoryModel by its name.
     *
     * @param categoryName The name of the category to retrieve.
     * @return The CategoryModel with the specified name.
     */
    CategoryModel getCategoryByName(String categoryName);

    /**
     * Retrieves a paginated list of CategoryModels.
     *
     * @param page      The page number (0-based).
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A list of CategoryModels representing the requested page.
     */
    List<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}