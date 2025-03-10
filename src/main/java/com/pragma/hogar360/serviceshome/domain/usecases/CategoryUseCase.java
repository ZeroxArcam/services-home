package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;

import java.util.List;

/**
 * Use case implementation for category operations.
 * Implements the CategoryServicePort interface.
 */
public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;

    /**
     * Constructs a new CategoryUseCase with the given CategoryPersistencePort.
     *
     * @param categoryPersistencePort The persistence port for category operations.
     */
    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    /**
     * Saves a CategoryModel.
     * Throws CategoryAlreadyExistsException if a category with the same name already exists.
     *
     * @param categoryModel The CategoryModel to be saved.
     */
    @Override
    public void save(CategoryModel categoryModel) {
        CategoryModel category = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (category != null) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    /**
     * Retrieves a paginated list of CategoryModels.
     *
     * @param page      The page number (0-based).
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A list of CategoryModels representing the requested page.
     */
    @Override
    public List<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryPersistencePort.getCategories(page, size, orderAsc);
    }

    /**
     * Retrieves a CategoryModel by its name.
     *
     * @param name The name of the category to retrieve.
     * @return The CategoryModel object if found, or null if not found.
     */
    @Override
    public CategoryModel getCategoryByName(String name) {
        return categoryPersistencePort.getCategoryByName(name);
    }
}