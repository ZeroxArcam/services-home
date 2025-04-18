package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.factory.CityModelFactory;
import com.pragma.hogar360.serviceshome.factory.LocationModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @Mock
    private CityPersistencePort cityPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Success() {
        // Arrange
        CityModel city = CityModelFactory.createDefaultCityModel();
        LocationModel location = LocationModelFactory.createLocationModel(1L, city, "Test Neighborhood");

        when(cityPersistencePort.existsById(city.getId())).thenReturn(true);
        when(locationPersistencePort.existsByNeighborhood(location.getNeighborhood())).thenReturn(false);
        when(locationPersistencePort.existsByCityId(city.getId())).thenReturn(false);

        // Act
        locationUseCase.save(location);

        // Assert
        verify(locationPersistencePort, times(1)).save(location);
    }

    @Test
    void testSave_CityNotFound() {
        // Arrange
        CityModel city = CityModelFactory.createDefaultCityModel();
        LocationModel location = LocationModelFactory.createLocationModel(1L, city, "Test Neighborhood");

        when(cityPersistencePort.existsById(city.getId())).thenReturn(false);

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> locationUseCase.save(location));
        verify(locationPersistencePort, never()).save(location);
    }
    @Test
    void testSave_CityNullOrIdNull() {
        // Arrange
        LocationModel location = LocationModelFactory.createDefaultLocationModel();
        location.setCity(null);

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> locationUseCase.save(location));

        // Arrange 2
        location.setCity(CityModelFactory.createDefaultCityModel());
        location.getCity().setId(null);

        // Act & Assert 2
        assertThrows(CityNotFoundException.class, () -> locationUseCase.save(location));
        verify(locationPersistencePort, never()).save(location);
    }

    @Test
    void testSave_DuplicateLocation() {
        // Arrange
        CityModel city = CityModelFactory.createDefaultCityModel();
        LocationModel location = LocationModelFactory.createLocationModel(1L, city, "Test Neighborhood");

        when(cityPersistencePort.existsById(city.getId())).thenReturn(true);
        when(locationPersistencePort.existsByNeighborhood(location.getNeighborhood())).thenReturn(true);
        when(locationPersistencePort.existsByCityId(city.getId())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateLocationException.class, () -> locationUseCase.save(location));
        verify(locationPersistencePort, never()).save(location);
    }

    @Test
    void testSave_IllegalArgumentException() {
        // Arrange
        CityModel city = CityModelFactory.createDefaultCityModel();
        LocationModel location = LocationModelFactory.createLocationModel(1L, city, null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> locationUseCase.save(location));
        verify(locationPersistencePort, never()).save(location);
    }

    @Test
    void testGetLocations_Success() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "cityName";
        String sortDirection = "ASC";
        String text = "";
        Pagination<LocationModel> pagination = new Pagination<>();

        when(locationPersistencePort.getLocations(page, size, sortBy, sortDirection, text)).thenReturn(pagination);

        // Act
        Pagination<LocationModel> result = locationUseCase.getLocations(page, size, sortBy, sortDirection, text);

        // Assert
        assertNotNull(result);
        assertEquals(pagination, result);
        verify(locationPersistencePort, times(1)).getLocations(page, size, sortBy, sortDirection, text);
    }

    @Test
    void testGetLocations_InvalidSortBy() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "invalidSortBy";
        String sortDirection = "ASC";
        String text = "";

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> locationUseCase.getLocations(page, size, sortBy, sortDirection, text));
        verify(locationPersistencePort, never()).getLocations(anyInt(), anyInt(), anyString(), anyString(), anyString());
    }

    @Test
    void testGetLocations_InvalidSortDirection() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "cityName";
        String sortDirection = "invalidSortDirection";
        String text = "";

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> locationUseCase.getLocations(page, size, sortBy, sortDirection, text));
        verify(locationPersistencePort, never()).getLocations(anyInt(), anyInt(), anyString(), anyString(), anyString());
    }
}