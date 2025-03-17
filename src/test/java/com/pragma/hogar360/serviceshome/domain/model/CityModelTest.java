package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityModelTest {

    @Test
    void constructor_shouldCreateCityModel_whenInputIsValid() {
        assertDoesNotThrow(() -> new CityModel(1L, "Valid City", "Valid Description"));
    }

    @Test
    void setName_shouldSetNameToValidName_whenInputIsValid() {
        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
        cityModel.setName("New Valid Name");
        assertEquals("New Valid Name", cityModel.getName());
    }

    @Test
    void setName_shouldThrowNullPointerException_whenNameIsNull() {
        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
        assertThrows(NullPointerException.class, () -> cityModel.setName(null));
    }

    @Test
    void setName_shouldThrowNameMaxSizeExceededException_whenNameIsTooLong() {
        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_LENGTH + 1);
        assertThrows(NameMaxSizeExceededException.class, () -> cityModel.setName(longName));
    }

    @Test
    void setName_shouldThrowEmptyNameException_whenNameIsBlank() {
        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
        assertThrows(EmptyNameException.class, () -> cityModel.setName("  "));
    }

    @Test
    void setDescription_shouldSetDescriptionToValidDescription_whenInputIsValid() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
        cityModel.setDescription("New Valid Description");
        assertEquals("New Valid Description", cityModel.getDescription());
    }

    @Test
    void setDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
        assertThrows(NullPointerException.class, () -> cityModel.setDescription(null));
    }

    @Test
    void setDescription_shouldThrowCityDepartmentDescriptionMaxSizeExceededException_whenDescriptionIsTooLong() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH + 1);
        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> cityModel.setDescription(longDescription));
    }

    @Test
    void setDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsBlank() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
        assertThrows(EmptyDescriptionException.class, () -> cityModel.setDescription("  "));
    }

    @Test
    void getId_shouldReturnCorrectId() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Valid Description");
        assertEquals(1L, cityModel.getId());
    }

    @Test
    void getName_shouldReturnCorrectName() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Valid Description");
        assertEquals("Valid City", cityModel.getName());
    }

    @Test
    void getDescription_shouldReturnCorrectDescription() {
        CityModel cityModel = new CityModel(1L, "Valid City", "Valid Description");
        assertEquals("Valid Description", cityModel.getDescription());
    }
}