package com.pragma.hogar360.serviceshome.domain.utils.constants;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Validation} utility class.
 * This class verifies that the validation methods in Validate throw the correct exceptions
 * when provided with invalid data.
 */

class ValidationTest {

    @Test
    void validateName_shouldNotThrowException_whenNameIsValid() {
        assertDoesNotThrow(() -> Validation.validateName("Valid Name"));
    }

    @Test
    void validateName_shouldThrowNullPointerException_whenNameIsNull() {
        assertThrows(NullPointerException.class, () -> Validation.validateName(null));
    }

    @Test
    void validateName_shouldThrowNameMaxSizeExceededException_whenNameIsTooLong() {
        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_LENGTH + 1);
        assertThrows(NameMaxSizeExceededException.class, () -> Validation.validateName(longName));
    }

    @Test
    void validateName_shouldThrowEmptyNameException_whenNameIsBlank() {
        assertThrows(EmptyNameException.class, () -> Validation.validateName("  "));
    }

    @Test
    void validateDescription_shouldNotThrowException_whenDescriptionIsValid() {
        assertDoesNotThrow(() -> Validation.validateDescription("Valid Description"));
    }

    @Test
    void validateDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
        assertThrows(NullPointerException.class, () -> Validation.validateDescription(null));
    }

    @Test
    void validateDescription_shouldThrowDescriptionMaxSizeExceededException_whenDescriptionIsTooLong() {
        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH + 1);
        assertThrows(DescriptionMaxSizeExceededException.class, () -> Validation.validateDescription(longDescription));
    }

    @Test
    void validateDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsBlank() {
        assertThrows(EmptyDescriptionException.class, () -> Validation.validateDescription("  "));
    }

    @Test
    void validationCityDepartmentDescription_shouldNotThrowException_whenDescriptionIsValid() {
        assertDoesNotThrow(() -> Validation.validationCityDepartmentDescription("Valid Description"));
    }

    @Test
    void validationCityDepartmentDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
        assertThrows(NullPointerException.class, () -> Validation.validationCityDepartmentDescription(null));
    }

    @Test
    void validationCityDepartmentDescription_shouldThrowCityDepartmentDescriptionMaxSizeExceededException_whenDescriptionIsTooLong() {
        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH + 1);
        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> Validation.validationCityDepartmentDescription(longDescription));
    }

    @Test
    void validationCityDepartmentDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsBlank() {
        assertThrows(EmptyDescriptionException.class, () -> Validation.validationCityDepartmentDescription("  "));
    }




}