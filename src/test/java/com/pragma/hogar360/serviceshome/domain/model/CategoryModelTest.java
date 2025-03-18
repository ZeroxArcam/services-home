//package com.pragma.hogar360.serviceshome.domain.model;
//import com.pragma.hogar360.serviceshome.domain.exceptions.DescriptionMaxSizeExceededException;
//import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;
//import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Unit tests for the {@link CategoryModel} class.
// * This class verifies the correct behavior of the CategoryModel's constructors,
// * getters, setters, and validation methods.
// */
//class CategoryModelTest {
//
//    private CategoryModel categoryModel;
//    private static final Long VALID_ID = 1L;
//    private static final String VALID_NAME = "Valid Name";
//    private static final String VALID_DESCRIPTION = "Valid Description";
//    private static final String INVALID_NAME = "a".repeat(51); // Max length is 50
//    private static final String INVALID_DESCRIPTION = "a".repeat(91); // Max length is 90
//
//    /**
//     * Sets up the test environment before each test method.
//     * Initializes a valid CategoryModel instance for use in tests.
//     */
//    @BeforeEach
//    void setUp() {
//        categoryModel = new CategoryModel(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
//    }
//
//    /**
//     * Tests the constructor with valid data to ensure a CategoryModel is created correctly.
//     */
//    @Test
//    void constructor_validData_shouldCreateCategoryModel() {
//        assertEquals(VALID_ID, categoryModel.getId());
//        assertEquals(VALID_NAME, categoryModel.getName());
//        assertEquals(VALID_DESCRIPTION, categoryModel.getDescription());
//    }
//
//    /**
//     * Tests the constructor to ensure it throws NameMaxSizeExceededException when the name exceeds the maximum length.
//     */
//    @Test
//    void constructor_nameExceedsMaxLength_shouldThrowException() {
//        assertThrows(NameMaxSizeExceededException.class, () -> new CategoryModel(VALID_ID, INVALID_NAME, VALID_DESCRIPTION));
//    }
//
//    /**
//     * Tests the constructor to ensure it throws DescriptionMaxSizeExceededException when the description exceeds the maximum length.
//     */
//    @Test
//    void constructor_descriptionExceedsMaxLength_shouldThrowException() {
//        assertThrows(DescriptionMaxSizeExceededException.class, () -> new CategoryModel(VALID_ID, VALID_NAME, INVALID_DESCRIPTION));
//    }
//
//    /**
//     * Tests the setName method with a valid name to ensure it sets the name correctly.
//     */
//    @Test
//    void setName_validName_shouldSetName() {
//        String newName = "New Name";
//        categoryModel.setName(newName);
//        assertEquals(newName, categoryModel.getName());
//    }
//
//    /**
//     * Tests the setDescription method with a valid description to ensure it sets the description correctly.
//     */
//    @Test
//    void setDescription_validDescription_shouldSetDescription() {
//        String newDescription = "New Description";
//        categoryModel.setDescription(newDescription);
//        assertEquals(newDescription, categoryModel.getDescription());
//    }
//
//    /**
//     * Tests the setName method to ensure it throws EmptyNameException when the name is empty.
//     */
//    @Test
//    void setName_invalidName_shouldThrowException() {
//        assertThrows(EmptyNameException.class, () -> categoryModel.setName(""));
//    }
//
//    /**
//     * Tests the setDescription method to ensure it throws NullPointerException when the description is null.
//     */
//    @Test
//    void setDescription_invalidDescription_shouldThrowException() {
//        assertThrows(NullPointerException.class, () -> categoryModel.setDescription(null));
//    }
//
//    /**
//     * Tests the toString method to ensure it returns the correct string representation.
//     */
//    @Test
//    void toString_shouldReturnCorrectString() {
//        String expectedString = "CategoryModel{id=1, name='Valid Name', description='Valid Description'}";
//        assertEquals(expectedString, categoryModel.toString());
//    }
//}

package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyDescriptionException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;
import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.factory.CategoryModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryModelTest {

    @Test
    public void testCreateCategoryModel() {
        // Arrange
        Long id = 1L;
        String name = "Electronics";
        String description = "Devices and gadgets";

        // Act
        CategoryModel categoryModel = new CategoryModel(id, name, description);

        // Assert
        assertEquals(id, categoryModel.getId());
        assertEquals(name, categoryModel.getName());
        assertEquals(description, categoryModel.getDescription());
    }

    @Test
    public void testSetId() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        Long newId = 2L;

        // Act
        categoryModel.setId(newId);

        // Assert
        assertEquals(newId, categoryModel.getId());
    }

    @Test
    public void testSetName_ValidName() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        String newName = "New Category Name";

        // Act
        categoryModel.setName(newName);

        // Assert
        assertEquals(newName, categoryModel.getName());
    }

    @Test
    public void testSetName_EmptyName() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();

        // Act & Assert
        assertThrows(EmptyNameException.class, () -> categoryModel.setName(""));
    }

    @Test
    public void testSetName_NullName() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> categoryModel.setName(null));
    }

    @Test
    public void testSetName_NameExceedsMaxLength() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        String longName = "This is a very long category name that exceeds the maximum allowed length.";

        // Act & Assert
        assertThrows(NameMaxSizeExceededException.class, () -> categoryModel.setName(longName));
    }

    @Test
    public void testSetDescription_ValidDescription() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        String newDescription = "New category description";

        // Act
        categoryModel.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, categoryModel.getDescription());
    }

    @Test
    public void testSetDescription_EmptyDescription() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();

        // Act & Assert
        assertThrows(EmptyDescriptionException.class, () -> categoryModel.setDescription(""));
    }

    @Test
    public void testSetDescription_NullDescription() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> categoryModel.setDescription(null));
    }

    @Test
    public void testSetDescription_DescriptionExceedsMaxLength() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();
        String longDescription = "This is a very long category description that exceeds the maximum allowed length. This is a very long category description that exceeds the maximum allowed length.";

        // Act & Assert
        assertThrows(DescriptionMaxSizeExceededException.class, () -> categoryModel.setDescription(longDescription));
    }

    @Test
    public void testToString() {
        // Arrange
        CategoryModel categoryModel = CategoryModelFactory.createDefaultCategoryModel();

        // Act
        String result = categoryModel.toString();

        // Assert
        assertTrue(result.contains("CategoryModel"));
        assertTrue(result.contains(categoryModel.getId().toString()));
        assertTrue(result.contains(categoryModel.getName()));
        assertTrue(result.contains(categoryModel.getDescription()));
    }
}