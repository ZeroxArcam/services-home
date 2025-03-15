package com.pragma.hogar360.serviceshome.domain.utils.constants;

import com.pragma.hogar360.serviceshome.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyDescriptionException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;

import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Validate} utility class.
 * This class verifies that the validation methods in Validate throw the correct exceptions
 * when provided with invalid data.
 */
class ValidateTest {

    /**
     * Tests that validateName throws NullPointerException when the name is null.
     */
    @Test
    void validateName_shouldThrowNullPointerException_whenNameIsNull() {
        assertThrows(NullPointerException.class, () -> Validate.validateName(null));
    }

    /**
     * Tests that validateName throws NameMaxSizeExceededException when the name exceeds the maximum length.
     */
    @Test
    void validateName_shouldThrowNameMaxSizeExceededException_whenNameExceedsMaxLength() {
        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_LENGTH + 1);
        assertThrows(NameMaxSizeExceededException.class, () -> Validate.validateName(longName));
    }

    /**
     * Tests that validateName throws EmptyNameException when the name is empty.
     */
    @Test
    void validateName_shouldThrowEmptyNameException_whenNameIsEmpty() {
        assertThrows(EmptyNameException.class, () -> Validate.validateName(""));
    }

    /**
     * Tests that validateDescription throws NullPointerException when the description is null.
     */
    @Test
    void validateDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
        assertThrows(NullPointerException.class, () -> Validate.validateDescription(null));
    }

    /**
     * Tests that validateDescription throws DescriptionMaxSizeExceededException when the description exceeds the maximum length.
     */
    @Test
    void validateDescription_shouldThrowDescriptionMaxSizeExceededException_whenDescriptionExceedsMaxLength() {
        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH + 1);
        assertThrows(DescriptionMaxSizeExceededException.class, () -> Validate.validateDescription(longDescription));
    }

    /**
     * Tests that validateDescription throws EmptyDescriptionException when the description is empty.
     */
    @Test
    void validateDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsEmpty() {
        assertThrows(EmptyDescriptionException.class, () -> Validate.validateDescription(""));
    }
}