package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.factory.LocationModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @Mock
    private CityPersistencePort cityPersistencePort;

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateLocation_Success() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();
        when(cityPersistencePort.existsByName(locationModel.getCityName())).thenReturn(true);
        when(departmentPersistencePort.existsByName(locationModel.getDepartmentName())).thenReturn(true);
        when(locationPersistencePort.existsByCityAndDepartment(locationModel.getCityName(), locationModel.getDepartmentName())).thenReturn(false);
        when(locationPersistencePort.saveLocation(locationModel)).thenReturn(locationModel);

        // Act
        LocationModel result = locationUseCase.createLocation(locationModel);

        // Assert
        assertNotNull(result);
        assertEquals(locationModel, result);
        verify(locationPersistencePort, times(1)).saveLocation(locationModel);
    }

    @Test
    public void testCreateLocation_CityNotFound() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();
        when(cityPersistencePort.existsByName(locationModel.getCityName())).thenReturn(false);

        // Act & Assert
        assertThrows(CityNotFoundException.class, () -> locationUseCase.createLocation(locationModel));
        verify(locationPersistencePort, never()).saveLocation(locationModel);
    }

    @Test
    public void testCreateLocation_DepartmentNotFound() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();
        when(cityPersistencePort.existsByName(locationModel.getCityName())).thenReturn(true);
        when(departmentPersistencePort.existsByName(locationModel.getDepartmentName())).thenReturn(false);

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () -> locationUseCase.createLocation(locationModel));
        verify(locationPersistencePort, never()).saveLocation(locationModel);
    }

    @Test
    public void testCreateLocation_DuplicateLocation() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();
        when(cityPersistencePort.existsByName(locationModel.getCityName())).thenReturn(true);
        when(departmentPersistencePort.existsByName(locationModel.getDepartmentName())).thenReturn(true);
        when(locationPersistencePort.existsByCityAndDepartment(locationModel.getCityName(), locationModel.getDepartmentName())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateLocationException.class, () -> locationUseCase.createLocation(locationModel));
        verify(locationPersistencePort, never()).saveLocation(locationModel);
    }

    @Test
    public void testGetLocations_Success() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "cityName";
        String sortDirection = "ASC";
        String text = "";
        List<LocationModel> locationList = new ArrayList<>();
        locationList.add(LocationModelFactory.createDefaultLocationModel());

        long totalElements = 1;
        int totalPages = 1;
        int pageNumber = 0;
        int pageSize = 10;

        Pagination<LocationModel> pagination = new Pagination<>(locationList, totalElements, totalPages, pageNumber, pageSize);

        when(locationPersistencePort.getLocations(page, size, sortBy, sortDirection, "")).thenReturn(pagination);

        // Act
        Pagination<LocationModel> result = locationUseCase.getLocations(page, size, sortBy, sortDirection, text);

        // Assert
        assertNotNull(result);
        assertEquals(pagination, result);
        verify(locationPersistencePort, times(1)).getLocations(page, size, sortBy, sortDirection, "");
    }

    @Test
    public void testGetLocations_InvalidSortBy() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "invalidSortBy";
        String sortDirection = "ASC";
        String text = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> locationUseCase.getLocations(page, size, sortBy, sortDirection, text));
        verify(locationPersistencePort, never()).getLocations(anyInt(), anyInt(), anyString(), anyString(), anyString());
    }

    @Test
    public void testGetLocations_InvalidSortDirection() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "cityName";
        String sortDirection = "invalidSortDirection";
        String text = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> locationUseCase.getLocations(page, size, sortBy, sortDirection, text));
        verify(locationPersistencePort, never()).getLocations(anyInt(), anyInt(), anyString(), anyString(), anyString());
    }
}