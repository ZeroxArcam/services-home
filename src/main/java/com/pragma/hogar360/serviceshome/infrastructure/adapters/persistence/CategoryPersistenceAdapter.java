package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Adapter class for category persistence operations using JPA.
 * Implements the CategoryPersistencePort interface.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    /**
     * Saves a CategoryModel to the database.
     *
     * @param categoryModel The CategoryModel to be saved.
     */
    @Override
    public void save(CategoryModel categoryModel) {
        categoryRepository.save(categoryEntityMapper.categoryModelToCategoryEntity(categoryModel));
    }

    /**
     * Retrieves a CategoryModel by its name.
     *
     * @param categoryName The name of the category to retrieve.
     * @return The CategoryModel with the specified name, or null if not found.
     */
    @Override
    public CategoryModel getCategoryByName(String categoryName) {
        return categoryEntityMapper.categoryEntityToCategoryModel(categoryRepository.findByName(categoryName).orElse(null));
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
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        return categoryEntityMapper.categoryEntityListToCategoryModelList(categoryRepository.findAll(pagination).getContent());
    }
    //made pageable with metadata

}