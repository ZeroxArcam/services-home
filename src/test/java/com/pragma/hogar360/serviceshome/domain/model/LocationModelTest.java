package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationModelTest {

    @Test
    void constructor_shouldCreateLocationModel_whenInputIsValid() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        assertDoesNotThrow(() -> new LocationModel(3L, city, department));
    }

    @Test
    void constructor_shouldThrowCityNotFoundException_whenCityIsNull() {
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        assertThrows(CityNotFoundException.class, () -> new LocationModel(3L, null, department));
    }

    @Test
    void constructor_shouldThrowDepartmentNotFoundException_whenDepartmentIsNull() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        assertThrows(DepartmentNotFoundException.class, () -> new LocationModel(3L, city, null));
    }

    @Test
    void getId_shouldReturnCorrectId() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        LocationModel location = new LocationModel(3L, city, department);
        assertEquals(3L, location.getId());
    }

    @Test
    void getCity_shouldReturnCorrectCity() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        LocationModel location = new LocationModel(3L, city, department);
        assertEquals(city, location.getCity());
    }

    @Test
    void getDepartment_shouldReturnCorrectDepartment() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        LocationModel location = new LocationModel(3L, city, department);
        assertEquals(department, location.getDepartment());
    }

    @Test
    void getCityName_shouldReturnCorrectCityName() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        LocationModel location = new LocationModel(3L, city, department);
        assertEquals("Test City", location.getCityName());
    }

    @Test
    void getDepartmentName_shouldReturnCorrectDepartmentName() {
        CityModel city = new CityModel(1L, "Test City", "Description");
        DepartmentModel department = new DepartmentModel(2L, "Test Department", "Description");
        LocationModel location = new LocationModel(3L, city, department);
        assertEquals("Test Department", location.getDepartmentName());
    }


}