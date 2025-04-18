package com.pragma.hogar360.serviceshome.application.services.implementation;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.application.mappers.CategoryDtoMapper;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CategoryServiceImplementation} class.
 * This class verifies the correct behavior of the CategoryServiceImplementation's methods,
 * ensuring that they interact correctly with the CategoryServicePort and CategoryDtoMapper.
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplementationTest {

    @Mock
    private CategoryServicePort categoryServicePort;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @InjectMocks
    private CategoryServiceImplementation categoryService;

    private SaveCategoryRequest saveCategoryRequest;
    private CategoryModel categoryModel;
    private CategoryResponse categoryResponse;
    private Pagination<CategoryModel> categoryPagination;

    /**
     * Sets up the test environment before each test method.
     * Initializes mock objects and test data for use in tests.
     */
    @BeforeEach
    void setUp() {
        saveCategoryRequest = new SaveCategoryRequest("Test Category", "Test Description");
        categoryModel = new CategoryModel(1L, "Test Category", "Test Description");
        categoryResponse = new CategoryResponse(1L, "Test Category", "Test Description");
        categoryPagination = new Pagination<>(Arrays.asList(categoryModel), 1, 1, 0, 1);
    }

    /**
     * Tests the save method to ensure it correctly saves a category and returns the expected response.
     */
    @Test
    void save_shouldSaveCategoryAndReturnResponse() {
        when(categoryDtoMapper.requestToModel(saveCategoryRequest)).thenReturn(categoryModel);

        SaveCategoryResponse response = categoryService.save(saveCategoryRequest);

        verify(categoryServicePort).save(categoryModel);
        assertEquals(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, response.message());
    }

    /**
     * Tests the getCategories method to ensure it returns a correctly paginated CategoryResponse.
     */
    @Test
    void getCategories_shouldReturnPagedCategoryResponse() {
        when(categoryServicePort.getCategories(0, 1, true)).thenReturn(categoryPagination);
        when(categoryDtoMapper.modelToResponse(categoryModel)).thenReturn(categoryResponse);

        PagedCategoryResponse response = categoryService.getCategories(0, 1, true);

        assertEquals(1, response.categories().size());
        assertEquals(1, response.totalElements());
        assertEquals(1, response.totalPages());
        assertEquals(0, response.pageNumber());
        assertEquals(1, response.pageSize());
    }

    /**
     * Tests the getCategoryByName method to ensure it returns the correct CategoryResponse for a given name.
     */
    @Test
    void getCategoryByName_shouldReturnCategoryResponse() {
        when(categoryServicePort.getCategoryByName("Test Category")).thenReturn(categoryModel);
        when(categoryDtoMapper.modelToResponse(categoryModel)).thenReturn(categoryResponse);

        CategoryResponse response = categoryService.getCategoryByName("Test Category");

        assertEquals(categoryResponse, response);
    }
}