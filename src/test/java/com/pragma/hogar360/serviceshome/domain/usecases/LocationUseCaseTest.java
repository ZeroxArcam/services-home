package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateLocationException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @Mock
    private CityPersistencePort cityPersistencePort;

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;

    private CityModel cityModel;
    private DepartmentModel departmentModel;
    private LocationModel locationModel;

    @BeforeEach
    void setUp() {
        cityModel = new CityModel(1L, "Test City", "Test Description");
        departmentModel = new DepartmentModel(2L, "Test Department", "Test Description");
        locationModel = new LocationModel(3L, cityModel, departmentModel);
    }

    @Test
    void createLocation_shouldCreateLocation_whenInputIsValid() {
        when(cityPersistencePort.existsByName("Test City")).thenReturn(true);
        when(departmentPersistencePort.existsByName("Test Department")).thenReturn(true);
        when(locationPersistencePort.existsByCityAndDepartment("Test City", "Test Department")).thenReturn(false);
        when(locationPersistencePort.saveLocation(locationModel)).thenReturn(locationModel);

        LocationModel result = locationUseCase.createLocation(locationModel);

        assertEquals(locationModel, result);
        verify(cityPersistencePort).existsByName("Test City");
        verify(departmentPersistencePort).existsByName("Test Department");
        verify(locationPersistencePort).existsByCityAndDepartment("Test City", "Test Department");
        verify(locationPersistencePort).saveLocation(locationModel);
    }

    @Test
    void createLocation_shouldThrowCityNotFoundException_whenCityDoesNotExist() {
        when(cityPersistencePort.existsByName("Test City")).thenReturn(false);

        assertThrows(CityNotFoundException.class, () -> locationUseCase.createLocation(locationModel));
        verify(cityPersistencePort).existsByName("Test City");
        verify(departmentPersistencePort, never()).existsByName(anyString());
        verify(locationPersistencePort, never()).existsByCityAndDepartment(anyString(), anyString());
        verify(locationPersistencePort, never()).saveLocation(any());
    }

    @Test
    void createLocation_shouldThrowDepartmentNotFoundException_whenDepartmentDoesNotExist() {
        when(cityPersistencePort.existsByName("Test City")).thenReturn(true);
        when(departmentPersistencePort.existsByName("Test Department")).thenReturn(false);

        assertThrows(DepartmentNotFoundException.class, () -> locationUseCase.createLocation(locationModel));
        verify(cityPersistencePort).existsByName("Test City");
        verify(departmentPersistencePort).existsByName("Test Department");
        verify(locationPersistencePort, never()).existsByCityAndDepartment(anyString(), anyString());
        verify(locationPersistencePort, never()).saveLocation(any());
    }

    @Test
    void createLocation_shouldThrowDuplicateLocationException_whenLocationExists() {
        when(cityPersistencePort.existsByName("Test City")).thenReturn(true);
        when(departmentPersistencePort.existsByName("Test Department")).thenReturn(true);
        when(locationPersistencePort.existsByCityAndDepartment("Test City", "Test Department")).thenReturn(true);

        assertThrows(DuplicateLocationException.class, () -> locationUseCase.createLocation(locationModel));
        verify(cityPersistencePort).existsByName("Test City");
        verify(departmentPersistencePort).existsByName("Test Department");
        verify(locationPersistencePort).existsByCityAndDepartment("Test City", "Test Department");
        verify(locationPersistencePort, never()).saveLocation(any());
    }
}