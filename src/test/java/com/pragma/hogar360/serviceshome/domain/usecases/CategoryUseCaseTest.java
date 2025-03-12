package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link CategoryUseCase}.
 * This class contains unit tests to verify the behavior of the CategoryUseCase class.
 */
@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private CategoryModel categoryModel;

    @BeforeEach
    void setUp() {
        categoryModel = new CategoryModel(1L, "Test Category", "Description");
    }

    /**
     * Tests the {@link CategoryUseCase#save(CategoryModel)} method.
     * Verifies that the method correctly saves a category when the category does not already exist.
     */
    @Test
    void save_shouldSaveCategory_whenCategoryDoesNotExist() {
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(null);
        categoryUseCase.save(categoryModel);
        verify(categoryPersistencePort, times(1)).save(categoryModel);
    }

    /**
     * Tests the {@link CategoryUseCase#save(CategoryModel)} method.
     * Verifies that the method throws CategoryAlreadyExistsException when the category already exists.
     */
    @Test
    void save_shouldThrowException_whenCategoryAlreadyExists() {
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(categoryModel);
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.save(categoryModel));
        verify(categoryPersistencePort, never()).save(categoryModel);
    }

    /**
     * Tests the {@link CategoryUseCase#getCategories(Integer, Integer, boolean)} method.
     * Verifies that the method correctly returns a list of categories.
     */
    @Test
    void getCategories_shouldReturnListOfCategories() {
        List<CategoryModel> categories = Arrays.asList(categoryModel, new CategoryModel(2L, "Category 2", "Desc 2"));
        when(categoryPersistencePort.getCategories(0, 10, true)).thenReturn(categories);
        List<CategoryModel> result = categoryUseCase.getCategories(0, 10, true);
        assertEquals(categories, result);
        verify(categoryPersistencePort, times(1)).getCategories(0, 10, true);
    }

    /**
     * Tests the {@link CategoryUseCase#getCategoryByName(String)} method.
     * Verifies that the method correctly returns a category when the category exists.
     */
    @Test
    void getCategoryByName_shouldReturnCategory_whenCategoryExists() {
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(categoryModel);
        CategoryModel result = categoryUseCase.getCategoryByName(categoryModel.getName());
        assertEquals(categoryModel, result);
        verify(categoryPersistencePort, times(1)).getCategoryByName(categoryModel.getName());
    }

    /**
     * Tests the {@link CategoryUseCase#getCategoryByName(String)} method.
     * Verifies that the method correctly returns null when the category does not exist.
     */
    @Test
    void getCategoryByName_shouldReturnNull_whenCategoryDoesNotExist() {
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(null);
        CategoryModel result = categoryUseCase.getCategoryByName(categoryModel.getName());
        assertNull(result);
        verify(categoryPersistencePort, times(1)).getCategoryByName(categoryModel.getName());
    }
}