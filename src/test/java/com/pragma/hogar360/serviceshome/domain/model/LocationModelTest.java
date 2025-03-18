//package com.pragma.hogar360.serviceshome.domain.model;

package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import com.pragma.hogar360.serviceshome.factory.CityModelFactory;
import com.pragma.hogar360.serviceshome.factory.DepartmentModelFactory;
import com.pragma.hogar360.serviceshome.factory.LocationModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationModelTest {

    @Test
    public void testCreateLocationModel() {
        // Arrange
        Long id = 1L;
        CityModel city = CityModelFactory.createDefaultCityModel();
        DepartmentModel department = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act
        LocationModel locationModel = new LocationModel(id, city, department);

        // Assert
        assertEquals(id, locationModel.getId());
        assertEquals(city, locationModel.getCity());
        assertEquals(department, locationModel.getDepartment());
    }

    @Test
    public void testCreateLocationModel_NullCity() {
        // Arrange
        DepartmentModel department = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> new LocationModel(1L, null, department));
    }

    @Test
    public void testCreateLocationModel_NullDepartment() {
        // Arrange
        CityModel city = CityModelFactory.createDefaultCityModel();

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () -> new LocationModel(1L, city, null));
    }

    @Test
    public void testGetCityName() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();

        // Act
        String cityName = locationModel.getCityName();

        // Assert
        assertEquals("Default City", cityName);
    }

    @Test
    public void testGetDepartmentName() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();

        // Act
        String departmentName = locationModel.getDepartmentName();

        // Assert
        assertEquals("Default Department", departmentName);
    }

    @Test
    public void testGetCityName_NullCity() {
        // Arrange
        DepartmentModel department = DepartmentModelFactory.createDefaultDepartmentModel();

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> new LocationModel(1L, null, department));
    }

    @Test
    public void testGetDepartmentName_NullDepartment() {
        // Arrange
        CityModel city = CityModelFactory.createDefaultCityModel();

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () -> new LocationModel(1L, city, null));
    }
    @Test
    public void testGetId() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();

        // Act
        Long result = locationModel.getId();

        // Assert
        assertEquals(1L, result);
    }
}

//import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
//import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LocationModelTest {
//
//    @Test
//    void constructor_shouldCreateLocationModel_whenInputIsValid() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        assertDoesNotThrow(() -> new LocationModel(3L, city, department));
//    }
//
//    @Test
//    void constructor_shouldThrowCityNotFoundException_whenCityIsNull() {
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        assertThrows(CityNotFoundException.class, () -> new LocationModel(3L, null, department));
//    }
//
//    @Test
//    void constructor_shouldThrowDepartmentNotFoundException_whenDepartmentIsNull() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        assertThrows(DepartmentNotFoundException.class, () -> new LocationModel(3L, city, null));
//    }
//
//    @Test
//    void getId_shouldReturnCorrectId() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        LocationModel location = new LocationModel(3L, city, department);
//        assertEquals(3L, location.getId());
//    }
//
//    @Test
//    void getCity_shouldReturnCorrectCity() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        LocationModel location = new LocationModel(3L, city, department);
//        assertEquals(city, location.getCity());
//    }
//
//    @Test
//    void getDepartment_shouldReturnCorrectDepartment() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        LocationModel location = new LocationModel(3L, city, department);
//        assertEquals(department, location.getDepartment());
//    }
//
//    @Test
//    void getCityName_shouldReturnCorrectCityName() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        LocationModel location = new LocationModel(3L, city, department);
//        assertEquals("Test City", location.getCityName());
//    }
//
//    @Test
//    void getDepartmentName_shouldReturnCorrectDepartmentName() {
//        CityModel city = new CityModel(1L, "Test City", "Description");
//        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
//        LocationModel location = new LocationModel(3L, city, department);
//        assertEquals("Test Department", location.getDepartmentName());
//    }
//
//
//}