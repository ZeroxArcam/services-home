package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentModelTest {

    @Test
    void constructor_shouldCreateDepartmentModel_whenInputIsValid() {
        assertDoesNotThrow(() -> new DepartmentModel(1L, "Valid Department", "Valid Description"));
    }

    @Test
    void setName_shouldSetNameToValidName_whenInputIsValid() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
        departmentModel.setName("New Valid Name");
        assertEquals("New Valid Name", departmentModel.getName());
    }

    @Test
    void setName_shouldThrowNullPointerException_whenNameIsNull() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
        assertThrows(NullPointerException.class, () -> departmentModel.setName(null));
    }

    @Test
    void setName_shouldThrowNameMaxSizeExceededException_whenNameIsTooLong() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_LENGTH + 1);
        assertThrows(NameMaxSizeExceededException.class, () -> departmentModel.setName(longName));
    }

    @Test
    void setName_shouldThrowEmptyNameException_whenNameIsBlank() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
        assertThrows(EmptyNameException.class, () -> departmentModel.setName("  "));
    }

    @Test
    void setDescription_shouldSetDescriptionToValidDescription_whenInputIsValid() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
        departmentModel.setDescription("New Valid Description");
        assertEquals("New Valid Description", departmentModel.getDescription());
    }

    @Test
    void setDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
        assertThrows(NullPointerException.class, () -> departmentModel.setDescription(null));
    }

    @Test
    void setDescription_shouldThrowCityDepartmentDescriptionMaxSizeExceededException_whenDescriptionIsTooLong() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH + 1);
        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> departmentModel.setDescription(longDescription));
    }

    @Test
    void setDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsBlank() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
        assertThrows(EmptyDescriptionException.class, () -> departmentModel.setDescription("  "));
    }

    @Test
    void getId_shouldReturnCorrectId() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Valid Description");
        assertEquals(1L, departmentModel.getId());
    }

    @Test
    void getName_shouldReturnCorrectName() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Valid Description");
        assertEquals("Valid Department", departmentModel.getName());
    }

    @Test
    void getDescription_shouldReturnCorrectDescription() {
        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Valid Description");
        assertEquals("Valid Description", departmentModel.getDescription());
    }
}