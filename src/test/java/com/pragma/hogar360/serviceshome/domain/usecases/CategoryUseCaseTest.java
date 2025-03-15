package com.pragma.hogar360.serviceshome.domain.usecases;
import com.pragma.hogar360.serviceshome.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360.serviceshome.domain.exceptions.NumberPageException;
import com.pragma.hogar360.serviceshome.domain.exceptions.SizePageException;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CategoryUseCase} class.
 * This class verifies the correct behavior of the CategoryUseCase's methods,
 * ensuring that they interact correctly with the CategoryPersistencePort.
 */
@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private CategoryModel categoryModel;
    private Pagination<CategoryModel> categoryPagination;

    /**
     * Sets up the test environment before each test method.
     * Initializes mock objects and test data for use in tests.
     */
    @BeforeEach
    void setUp() {
        categoryModel = new CategoryModel(1L, "Test Category", "Test Description");
        categoryPagination = new Pagination<>(Arrays.asList(categoryModel), 1, 1, 0, 1);
    }

    /**
     * Tests the save method to ensure it correctly saves a category when the category does not exist.
     */
    @Test
    void save_shouldSaveCategory_whenCategoryDoesNotExist() {
        when(categoryPersistencePort.getCategoryByName("Test Category")).thenReturn(null);

        categoryUseCase.save(categoryModel);

        verify(categoryPersistencePort).save(categoryModel);
    }

    /**
     * Tests the save method to ensure it throws CategoryAlreadyExistsException when the category already exists.
     */
    @Test
    void save_shouldThrowException_whenCategoryAlreadyExists() {
        when(categoryPersistencePort.getCategoryByName("Test Category")).thenReturn(categoryModel);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.save(categoryModel));
    }

    /**
     * Tests the getCategories method to ensure it returns a correctly paginated list of categories.
     */
    @Test
    void getCategories_shouldReturnPagedCategories() {
        when(categoryPersistencePort.getCategories(0, 1, true)).thenReturn(categoryPagination);

        Pagination<CategoryModel> response = categoryUseCase.getCategories(0, 1, true);

        assertEquals(categoryPagination, response);
    }

    /**
     * Tests the getCategories method to ensure it throws NumberPageException when the page number is invalid.
     */
    @Test
    void getCategories_shouldThrowNumberPageException_whenPageIsInvalid() {
        assertThrows(NumberPageException.class, () -> categoryUseCase.getCategories(-1, 1, true));
        assertThrows(NumberPageException.class, () -> categoryUseCase.getCategories(null, 1, true));
    }

    /**
     * Tests the getCategories method to ensure it throws SizePageException when the page size is invalid.
     */
    @Test
    void getCategories_shouldThrowSizePageException_whenSizeIsInvalid() {
        assertThrows(SizePageException.class, () -> categoryUseCase.getCategories(0, 0, true));
        assertThrows(SizePageException.class, () -> categoryUseCase.getCategories(0, null, true));
    }

    /**
     * Tests the getCategoryByName method to ensure it returns the correct CategoryModel for a given name.
     */
    @Test
    void getCategoryByName_shouldReturnCategoryModel() {
        when(categoryPersistencePort.getCategoryByName("Test Category")).thenReturn(categoryModel);

        CategoryModel response = categoryUseCase.getCategoryByName("Test Category");

        assertEquals(categoryModel, response);
    }
}