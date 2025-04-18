package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;
import com.pragma.hogar360.serviceshome.factory.CategoryModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Success() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(null);

        // Act
        categoryUseCase.save(categoryModel);

        // Assert
        verify(categoryPersistencePort, times(1)).save(categoryModel);
    }

    @Test
    void testSave_CategoryAlreadyExists() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(categoryModel);

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.save(categoryModel));
        verify(categoryPersistencePort, never()).save(categoryModel);
    }

    @Test
    void testGetCategories_Success() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean orderAsc = true;
        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(CategoryModelFactory.createDefaultCategoryModel());
        long totalElements = 1;
        int totalPages = 1;
        int pageNumber = 0;
        int pageSize = 10;

        Pagination<CategoryModel> pagination = new Pagination<>(categoryList, totalElements, totalPages, pageNumber, pageSize);
        when(categoryPersistencePort.getCategories(page, size, orderAsc)).thenReturn(pagination);

        // Act
        Pagination<CategoryModel> result = categoryUseCase.getCategories(page, size, orderAsc);

        // Assert
        assertNotNull(result);
        assertEquals(pagination, result);
        verify(categoryPersistencePort, times(1)).getCategories(page, size, orderAsc);
    }

    @Test
    void testGetCategoryByName_Success() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(categoryModel);

        // Act
        CategoryModel result = categoryUseCase.getCategoryByName(categoryModel.getName());

        // Assert
        assertNotNull(result);
        assertEquals(categoryModel, result);
        verify(categoryPersistencePort, times(1)).getCategoryByName(categoryModel.getName());
    }

    @Test
    void testGetCategoryByName_NotFound() {
        // Arrange
        String categoryName = "Nonexistent Category";
        when(categoryPersistencePort.getCategoryByName(categoryName)).thenReturn(null);

        // Act
        CategoryModel result = categoryUseCase.getCategoryByName(categoryName);

        // Assert
        assertNull(result);
        verify(categoryPersistencePort, times(1)).getCategoryByName(categoryName);
    }

    @Test
    void testSave_ValidCategory_SavesCategory() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        when(categoryPersistencePort.getCategoryByName(categoryModel.getName())).thenReturn(null);

        try (MockedStatic<Validation> mockedValidation = mockStatic(Validation.class)) {
            mockedValidation.when(() -> Validation.validateName(categoryModel.getName())).thenAnswer(invocation -> null);
            mockedValidation.when(() -> Validation.validateDescription(categoryModel.getDescription())).thenAnswer(invocation -> null);

            // Act
            categoryUseCase.save(categoryModel);

            // Assert
            verify(categoryPersistencePort, times(1)).save(categoryModel);
            mockedValidation.verify(() -> Validation.validateName(categoryModel.getName()));
            mockedValidation.verify(() -> Validation.validateDescription(categoryModel.getDescription()));
        }
    }


}