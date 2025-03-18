package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.CityDepartmentDescriptionMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyDescriptionException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;
import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.factory.DepartmentModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentModelTest {

    @Test
    public void testCreateDepartmentModel() {
        // Arrange
        Long id = 1L;
        String name = "Antioquia";
        String description = "Land of mountains";

        // Act
        DepartmentModel departmentModel = new DepartmentModel(id, name, description);

        // Assert
        assertEquals(id, departmentModel.getId());
        assertEquals(name, departmentModel.getName());
        assertEquals(description, departmentModel.getDescription());
    }

    @Test
    public void testSetName_ValidName() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        String newName = "New Department Name";

        // Act
        departmentModel.setName(newName);

        // Assert
        assertEquals(newName, departmentModel.getName());
    }

    @Test
    public void testSetName_EmptyName() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act & Assert
        assertThrows(EmptyNameException.class, () -> departmentModel.setName(""));
    }

    @Test
    public void testSetName_NullName() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> departmentModel.setName(null));
    }

    @Test
    public void testSetName_NameExceedsMaxLength() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        String longName = "This is a very long department name that exceeds the maximum allowed length.This is a very long department name that exceeds the maximum allowed length.";

        // Act & Assert
        assertThrows(NameMaxSizeExceededException.class, () -> departmentModel.setName(longName));
    }

    @Test
    public void testSetDescription_ValidDescription() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        String newDescription = "New department description";

        // Act
        departmentModel.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, departmentModel.getDescription());
    }

    @Test
    public void testSetDescription_EmptyDescription() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act & Assert
        assertThrows(EmptyDescriptionException.class, () -> departmentModel.setDescription(""));
    }

    @Test
    public void testSetDescription_NullDescription() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> departmentModel.setDescription(null));
    }

    @Test
    public void testSetDescription_DescriptionExceedsMaxLength() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        String longDescription = "This is a very long department description that exceeds the maximum allowed length. This is a very long department description that exceeds the maximum allowed length. This is a very long department description that exceeds the maximum allowed length. This is a very long department description that exceeds the maximum allowed length.This is a very long department description that exceeds the maximum allowed length. This is a very long department description that exceeds the maximum allowed length.";

        // Act & Assert
        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> departmentModel.setDescription(longDescription));
    }

    @Test
    public void testGetId() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act
        Long result = departmentModel.getId();

        // Assert
        assertEquals(1L, result);
    }
}
//import com.pragma.hogar360.serviceshome.domain.exceptions.*;
//import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DepartmentModelTest {
//
//    @Test
//    void constructor_shouldCreateDepartmentModel_whenInputIsValid() {
//        assertDoesNotThrow(() -> new DepartmentModel(1L, "Valid Department", "Valid Description"));
//    }
//
//    @Test
//    void setName_shouldSetNameToValidName_whenInputIsValid() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
//        departmentModel.setName("New Valid Name");
//        assertEquals("New Valid Name", departmentModel.getName());
//    }
//
//    @Test
//    void setName_shouldThrowNullPointerException_whenNameIsNull() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
//        assertThrows(NullPointerException.class, () -> departmentModel.setName(null));
//    }
//
//    @Test
//    void setName_shouldThrowNameMaxSizeExceededException_whenNameIsTooLong() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
//        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_LENGTH + 1);
//        assertThrows(NameMaxSizeExceededException.class, () -> departmentModel.setName(longName));
//    }
//
//    @Test
//    void setName_shouldThrowEmptyNameException_whenNameIsBlank() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Initial Name", "Valid Description");
//        assertThrows(EmptyNameException.class, () -> departmentModel.setName("  "));
//    }
//
//    @Test
//    void setDescription_shouldSetDescriptionToValidDescription_whenInputIsValid() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
//        departmentModel.setDescription("New Valid Description");
//        assertEquals("New Valid Description", departmentModel.getDescription());
//    }
//
//    @Test
//    void setDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
//        assertThrows(NullPointerException.class, () -> departmentModel.setDescription(null));
//    }
//
//    @Test
//    void setDescription_shouldThrowCityDepartmentDescriptionMaxSizeExceededException_whenDescriptionIsTooLong() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
//        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH + 1);
//        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> departmentModel.setDescription(longDescription));
//    }
//
//    @Test
//    void setDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsBlank() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Initial Description");
//        assertThrows(EmptyDescriptionException.class, () -> departmentModel.setDescription("  "));
//    }
//
//    @Test
//    void getId_shouldReturnCorrectId() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Valid Description");
//        assertEquals(1L, departmentModel.getId());
//    }
//
//    @Test
//    void getName_shouldReturnCorrectName() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Valid Description");
//        assertEquals("Valid Department", departmentModel.getName());
//    }
//
//    @Test
//    void getDescription_shouldReturnCorrectDescription() {
//        DepartmentModel departmentModel = new DepartmentModel(1L, "Valid Department", "Valid Description");
//        assertEquals("Valid Description", departmentModel.getDescription());
//    }
//}