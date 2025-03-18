package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.factory.CityModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityModelTest {

    @Test
    public void testCreateCityModel() {
        // Arrange
        Long id = 1L;
        String name = "Medellin";
        String description = "City of eternal spring";

        // Act
        CityModel cityModel = new CityModel(id, name, description);

        // Assert
        assertEquals(id, cityModel.getId());
        assertEquals(name, cityModel.getName());
        assertEquals(description, cityModel.getDescription());
    }

    @Test
    public void testSetName_ValidName() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        String newName = "New City Name";

        // Act
        cityModel.setName(newName);

        // Assert
        assertEquals(newName, cityModel.getName());
    }

    @Test
    public void testSetName_EmptyName() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();

        // Act & Assert
        assertThrows(EmptyNameException.class, () -> cityModel.setName(""));
    }

    @Test
    public void testSetName_NullName() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> cityModel.setName(null));
    }

    @Test
    public void testSetName_NameExceedsMaxLength() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        String longName = "This is a very long city name that exceeds the maximum allowed length.";

        // Act & Assert
        assertThrows(NameMaxSizeExceededException.class, () -> cityModel.setName(longName));
    }

    @Test
    public void testSetDescription_ValidDescription() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        String newDescription = "New city description";

        // Act
        cityModel.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, cityModel.getDescription());
    }

    @Test
    public void testSetDescription_EmptyDescription() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();

        // Act & Assert
        assertThrows(EmptyDescriptionException.class, () -> cityModel.setDescription(""));
    }

    @Test
    public void testSetDescription_NullDescription() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> cityModel.setDescription(null));
    }

    @Test
    public void testSetDescription_DescriptionExceedsMaxLength() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        String longDescription = "This is a very long city description that exceeds the maximum allowed length. This is a very long city description that exceeds the maximum allowed length. This is a very long city description that exceeds the maximum allowed length. This is a very long city description that exceeds the maximum allowed length.This is a very long city description that exceeds the maximum allowed length. This is a very long city description that exceeds the maximum allowed length."; // Asegúrate de que la longitud sea mayor que 90

        // Act & Assert
        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> cityModel.setDescription(longDescription));
    }

    @Test
    public void testGetId() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();

        // Act
        Long result = cityModel.getId();

        // Assert
        assertEquals(1L, result);
    }
}


//
//import com.pragma.hogar360.serviceshome.domain.exceptions.*;
//import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CityModelTest {
//
//    @Test
//    void constructor_shouldCreateCityModel_whenInputIsValid() {
//        assertDoesNotThrow(() -> new CityModel(1L, "Valid City", "Valid Description"));
//    }
//
//    @Test
//    void setName_shouldSetNameToValidName_whenInputIsValid() {
//        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
//        cityModel.setName("New Valid Name");
//        assertEquals("New Valid Name", cityModel.getName());
//    }
//
//    @Test
//    void setName_shouldThrowNullPointerException_whenNameIsNull() {
//        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
//        assertThrows(NullPointerException.class, () -> cityModel.setName(null));
//    }
//
//    @Test
//    void setName_shouldThrowNameMaxSizeExceededException_whenNameIsTooLong() {
//        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
//        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_LENGTH + 1);
//        assertThrows(NameMaxSizeExceededException.class, () -> cityModel.setName(longName));
//    }
//
//    @Test
//    void setName_shouldThrowEmptyNameException_whenNameIsBlank() {
//        CityModel cityModel = new CityModel(1L, "Initial Name", "Valid Description");
//        assertThrows(EmptyNameException.class, () -> cityModel.setName("  "));
//    }
//
//    @Test
//    void setDescription_shouldSetDescriptionToValidDescription_whenInputIsValid() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
//        cityModel.setDescription("New Valid Description");
//        assertEquals("New Valid Description", cityModel.getDescription());
//    }
//
//    @Test
//    void setDescription_shouldThrowNullPointerException_whenDescriptionIsNull() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
//        assertThrows(NullPointerException.class, () -> cityModel.setDescription(null));
//    }
//
//    @Test
//    void setDescription_shouldThrowCityDepartmentDescriptionMaxSizeExceededException_whenDescriptionIsTooLong() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
//        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH + 1);
//        assertThrows(CityDepartmentDescriptionMaxSizeExceededException.class, () -> cityModel.setDescription(longDescription));
//    }
//
//    @Test
//    void setDescription_shouldThrowEmptyDescriptionException_whenDescriptionIsBlank() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Initial Description");
//        assertThrows(EmptyDescriptionException.class, () -> cityModel.setDescription("  "));
//    }
//
//    @Test
//    void getId_shouldReturnCorrectId() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Valid Description");
//        assertEquals(1L, cityModel.getId());
//    }
//
//    @Test
//    void getName_shouldReturnCorrectName() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Valid Description");
//        assertEquals("Valid City", cityModel.getName());
//    }
//
//    @Test
//    void getDescription_shouldReturnCorrectDescription() {
//        CityModel cityModel = new CityModel(1L, "Valid City", "Valid Description");
//        assertEquals("Valid Description", cityModel.getDescription());
//    }
//}