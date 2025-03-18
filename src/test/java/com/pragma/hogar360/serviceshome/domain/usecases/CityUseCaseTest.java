package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.factory.CityModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityUseCaseTest {

    @Mock
    private CityPersistencePort cityPersistencePort;

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private CityUseCase cityUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCity_Success() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        when(cityPersistencePort.existsByName(cityModel.getName())).thenReturn(false);
        when(cityPersistencePort.saveCity(cityModel)).thenReturn(cityModel);

        // Act
        CityModel result = cityUseCase.createCity(cityModel);

        // Assert
        assertNotNull(result);
        assertEquals(cityModel, result);
        verify(cityPersistencePort, times(1)).existsByName(cityModel.getName());
        verify(cityPersistencePort, times(1)).saveCity(cityModel);
    }

    @Test
    public void testCreateCity_DuplicateName() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        when(cityPersistencePort.existsByName(cityModel.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateDepartmentNameException.class, () -> cityUseCase.createCity(cityModel));
        verify(cityPersistencePort, times(1)).existsByName(cityModel.getName());
        verify(cityPersistencePort, never()).saveCity(cityModel);
    }

    @Test
    public void testGetCityByName_Success() {
        // Arrange
        CityModel cityModel = CityModelFactory.createDefaultCityModel();
        when(cityPersistencePort.findByName(cityModel.getName())).thenReturn(Optional.of(cityModel));

        // Act
        Optional<CityModel> result = cityUseCase.getCityByName(cityModel.getName());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(cityModel, result.get());
        verify(cityPersistencePort, times(1)).findByName(cityModel.getName());
    }

    @Test
    public void testGetCityByName_NotFound() {
        // Arrange
        String cityName = "Nonexistent City";
        when(cityPersistencePort.findByName(cityName)).thenReturn(Optional.empty());

        // Act
        Optional<CityModel> result = cityUseCase.getCityByName(cityName);

        // Assert
        assertFalse(result.isPresent());
        verify(cityPersistencePort, times(1)).findByName(cityName);
    }
}