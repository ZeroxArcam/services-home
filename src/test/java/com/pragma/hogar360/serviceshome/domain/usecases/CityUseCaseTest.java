package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityUseCaseTest {

    @Mock
    private CityPersistencePort cityPersistencePort;

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private CityUseCase cityUseCase;

    private CityModel cityModel;

    @BeforeEach
    void setUp() {
        cityModel = new CityModel(1L, "Test City", "Test Description");
    }

    @Test
    void createCity_shouldCreateCity_whenNameIsUnique() {
        when(cityPersistencePort.existsByName("Test City")).thenReturn(false);
        when(cityPersistencePort.saveCity(cityModel)).thenReturn(cityModel);

        CityModel result = cityUseCase.createCity(cityModel);

        assertEquals(cityModel, result);
        verify(cityPersistencePort).existsByName("Test City");
        verify(cityPersistencePort).saveCity(cityModel);
    }

    @Test
    void createCity_shouldThrowDuplicateDepartmentNameException_whenNameExists() {
        when(cityPersistencePort.existsByName("Test City")).thenReturn(true);

        assertThrows(DuplicateDepartmentNameException.class, () -> cityUseCase.createCity(cityModel));
        verify(cityPersistencePort).existsByName("Test City");
        verify(cityPersistencePort, never()).saveCity(any());
    }

    @Test
    void getCityByName_shouldReturnCity_whenCityExists() {
        when(cityPersistencePort.findByName("Test City")).thenReturn(Optional.of(cityModel));

        Optional<CityModel> result = cityUseCase.getCityByName("Test City");

        assertTrue(result.isPresent());
        assertEquals(cityModel, result.get());
        verify(cityPersistencePort).findByName("Test City");
    }

    @Test
    void getCityByName_shouldReturnEmptyOptional_whenCityDoesNotExist() {
        when(cityPersistencePort.findByName("Nonexistent City")).thenReturn(Optional.empty());

        Optional<CityModel> result = cityUseCase.getCityByName("Nonexistent City");

        assertFalse(result.isPresent());
        verify(cityPersistencePort).findByName("Nonexistent City");
    }
}