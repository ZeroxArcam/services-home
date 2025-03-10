package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyDescriptionException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;
import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link CategoryModel}.
 * This class contains unit tests to verify the behavior of the CategoryModel class.
 */
class CategoryModelTest {

    /**
     * Tests the constructor when the name is null, expecting a NullPointerException.
     */
    @Test
    void testConstructor_nullName_throwsNullPointerException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                new CategoryModel(1L, null, "Description"));
        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    /**
     * Tests the constructor when the name exceeds the maximum allowed length, expecting a NameMaxSizeExceededException.
     */
    @Test
    void testConstructor_nameExceedsMaxLength_throwsNameMaxSizeExceededException() {
        String longName = "a".repeat(51); // Creates a name with 51 characters.
        assertThrows(NameMaxSizeExceededException.class, () ->
                new CategoryModel(1L, longName, "Description"));
    }

    /**
     * Tests the constructor when the name is within the maximum allowed length, expecting no exception.
     */
    @Test
    void testConstructor_nameWithinMaxLength_doesNotThrowException() {
        String validName = "a".repeat(50); // Creates a name with 50 characters.
        assertDoesNotThrow(() -> new CategoryModel(1L, validName, "Description"));
    }

    /**
     * Tests the constructor when the name is empty, expecting an EmptyNameException.
     */
    @Test
    void testConstructor_emptyName_throwsEmptyNameException() {
        assertThrows(EmptyNameException.class, () ->
                new CategoryModel(1L, "", "Description"));
    }

    /**
     * Tests the constructor when the name is blank (contains only spaces), expecting an EmptyNameException.
     */
    @Test
    void testConstructor_blankName_throwsEmptyNameException() {
        assertThrows(EmptyNameException.class, () ->
                new CategoryModel(1L, "   ", "Description"));
    }

    /**
     * Tests the constructor when the name is valid, expecting no exception.
     */
    @Test
    void testConstructor_validName_doesNotThrowException() {
        assertDoesNotThrow(() -> new CategoryModel(1L, "Valid Name", "Description"));
    }

    /**
     * Tests the constructor when the description exceeds the maximum allowed length, expecting a DescriptionMaxSizeExceededException.
     */
    @Test
    void testConstructor_descriptionExceedsMaxLength_throwsDescriptionMaxSizeExceededException() {
        String longDescription = "a".repeat(91); // Creates a description with 91 characters.
        assertThrows(DescriptionMaxSizeExceededException.class, () ->
                new CategoryModel(1L, "Name", longDescription));
    }

    /**
     * Tests the constructor when the description is within the maximum allowed length, expecting no exception.
     */
    @Test
    void testConstructor_descriptionWithinMaxLength_doesNotThrowException() {
        String validDescription = "a".repeat(90); // Creates a description with 90 characters.
        assertDoesNotThrow(() -> new CategoryModel(1L, "Name", validDescription));
    }

    /**
     * Tests the constructor when the description is empty, expecting an EmptyDescriptionException.
     */
    @Test
    void testConstructor_emptyDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () ->
                new CategoryModel(1L, "Name", ""));
    }

    /**
     * Tests the constructor when the description is blank (contains only spaces), expecting an EmptyDescriptionException.
     */
    @Test
    void testConstructor_blankDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () ->
                new CategoryModel(1L, "Name", "   "));
    }

    /**
     * Tests the constructor when the description is valid, expecting no exception.
     */
    @Test
    void testConstructor_validDescription_doesNotThrowException() {
        assertDoesNotThrow(() -> new CategoryModel(1L, "Name", "Valid Description"));
    }
}