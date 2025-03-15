package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.mappers.CategoryDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.CategoryService;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the CategoryService interface.
 * Provides concrete implementations for category-related business logic.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;

    /**
     * Saves a new category based on the provided request.
     *
     * @param request The SaveCategoryRequest containing the category data.
     * @return A SaveCategoryResponse containing the result of the save operation.
     */
    @Override
    public SaveCategoryResponse save(SaveCategoryRequest request) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(request));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    /**
     * Retrieves a paginated list of categories.
     *
     * @param page      The page number (0-based).
     * @param size      The number of categories per page.
     * @param orderAsc  True for ascending order, false for descending order.
     * @return A list of CategoryResponse DTOs representing the requested page.
     */
    @Override

    public PagedCategoryResponse getCategories(Integer page, Integer size, boolean orderAsc) {
        Pagination<CategoryModel> categoryPagination = categoryServicePort.getCategories(page, size, orderAsc);

        // Convert the list of CategoryModel to CategoryResponse
        List<CategoryResponse> categoryResponses = categoryPagination.getItems().stream()
                .map(categoryDtoMapper::modelToResponse)
                .toList();

        // Return the DTO with the correct conversion
        return new PagedCategoryResponse(
                categoryResponses, // converted list
                categoryPagination.getTotalElements(),
                categoryPagination.getTotalPages(),
                categoryPagination.getPageNumber(),
                categoryPagination.getPageSize()
        );
    }

    /**
     * Retrieves a category by its name and returns a CategoryResponse.
     *
     * @param name The name of the category to retrieve.
     * @return A CategoryResponse object representing the retrieved category, or null if not found.
     */
    @Override
    public CategoryResponse getCategoryByName(String name) {
        return categoryDtoMapper.modelToResponse(categoryServicePort.getCategoryByName(name));
    }
}