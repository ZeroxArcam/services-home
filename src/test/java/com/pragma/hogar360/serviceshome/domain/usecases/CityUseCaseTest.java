package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.factory.CityModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityUseCaseTest {

    @Mock
    private CityPersistencePort cityPersistencePort;

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    private CityUseCase cityUseCase;

    @BeforeEach
    void setUp() {
        cityUseCase = new CityUseCase(cityPersistencePort, departmentPersistencePort);
    }

    @Test
    void save_shouldSaveCity_whenCityIsValid() {
        // Arrange
        DepartmentModel department = CityModelFactory.createDepartmentModel(1L, "Test Department", "Test Department Description");
        CityModel city = CityModelFactory.createCityModel(1L, "Test City", "Test Description", department);

        when(cityPersistencePort.existsByNameAndDepartmentName(city.getName(), department.getName())).thenReturn(false);

        // Act
        cityUseCase.save(city);

        // Assert
        verify(cityPersistencePort, times(1)).save(city);
    }

    @Test
    void save_shouldThrowDepartmentNotFoundException_whenDepartmentDoesNotExist() {
        // Arrange
        CityModel city = CityModelFactory.createCityModelWithNullDepartment("Test City", "Test Description");

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () -> cityUseCase.save(city));
        verify(cityPersistencePort, never()).save(any());
    }

    @Test
    void save_shouldThrowDuplicateDepartmentNameException_whenCityAlreadyExists() {
        // Arrange
        DepartmentModel department = CityModelFactory.createDepartmentModel(1L, "Test Department", "Test Department Description");
        CityModel city = CityModelFactory.createCityModel(1L, "Test City", "Test Description", department);

        when(cityPersistencePort.existsByNameAndDepartmentName(city.getName(), department.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateDepartmentNameException.class, () -> cityUseCase.save(city));
        verify(cityPersistencePort, never()).save(any());
    }
}