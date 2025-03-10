package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.mappers.CategoryDtoMapper;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link CategoryServiceImplementation}.
 * This class contains unit tests to verify the behavior of the CategoryServiceImplementation class.
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplementationTest {

    @Mock
    private CategoryServicePort categoryServicePort;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @InjectMocks
    private CategoryServiceImplementation categoryService;

    /**
     * Tests the {@link CategoryServiceImplementation#save(SaveCategoryRequest)} method.
     * Verifies that the method correctly saves a category and returns the expected response.
     */
    @Test
    void save_shouldSaveCategoryAndReturnResponse() {
        // Arrange
        SaveCategoryRequest request = new SaveCategoryRequest("Test Category", "Test Description");
        CategoryModel model = new CategoryModel(null, "Test Category", "Test Description");
        when(categoryDtoMapper.requestToModel(request)).thenReturn(model);
        doNothing().when(categoryServicePort).save(model);

        // Act
        SaveCategoryResponse response = categoryService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, response.message());
        assertNotNull(response.message());
        verify(categoryDtoMapper).requestToModel(request);
        verify(categoryServicePort).save(model);
    }

    /**
     * Tests the {@link CategoryServiceImplementation#getCategories(Integer, Integer, boolean)} method.
     * Verifies that the method correctly retrieves paginated categories and returns the expected list of responses.
     */
    @Test
    void getCategories_shouldReturnPaginatedCategories() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean orderAsc = true;
        CategoryModel model1 = new CategoryModel(1L, "Category 1", "Description 1");
        CategoryModel model2 = new CategoryModel(2L, "Category 2", "Description 2");
        List<CategoryModel> modelList = Arrays.asList(model1, model2);
        CategoryResponse response1 = new CategoryResponse(1L, "Category 1", "Description 1");
        CategoryResponse response2 = new CategoryResponse(2L, "Category 2", "Description 2");
        List<CategoryResponse> responseList = Arrays.asList(response1, response2);

        when(categoryServicePort.getCategories(page, size, orderAsc)).thenReturn(modelList);
        when(categoryDtoMapper.modelListToResponseList(modelList)).thenReturn(responseList);

        // Act
        List<CategoryResponse> result = categoryService.getCategories(page, size, orderAsc);

        // Assert
        assertEquals(responseList, result);
        verify(categoryServicePort).getCategories(page, size, orderAsc);
        verify(categoryDtoMapper).modelListToResponseList(modelList);
    }

    /**
     * Tests the {@link CategoryServiceImplementation#getCategoryByName(String)} method.
     * Verifies that the method correctly retrieves a category by name and returns the expected response.
     */
    @Test
    void getCategoryByName_shouldReturnCategoryByName() {
        // Arrange
        String name = "Test Category";
        CategoryModel model = new CategoryModel(1L, "Test Category", "Test Description");
        CategoryResponse response = new CategoryResponse(1L, "Test Category", "Test Description");

        when(categoryServicePort.getCategoryByName(name)).thenReturn(model);
        when(categoryDtoMapper.modelToResponse(model)).thenReturn(response);

        // Act
        CategoryResponse result = categoryService.getCategoryByName(name);

        // Assert
        assertEquals(response, result);
        verify(categoryServicePort).getCategoryByName(name);
        verify(categoryDtoMapper).modelToResponse(model);
    }
}