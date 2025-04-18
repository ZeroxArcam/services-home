package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.*;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.factory.CategoryModelFactory;
import com.pragma.hogar360.serviceshome.factory.HomeModelFactory;
import com.pragma.hogar360.serviceshome.factory.HomeQueryModelFactory;
import com.pragma.hogar360.serviceshome.factory.LocationModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeUseCaseTest {

    @Mock
    private HomePersistencePort homePersistencePort;

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @InjectMocks
    private HomeUseCase homeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave_Success() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act
        homeUseCase.save(homeModel);

        // Assert
        verify(homePersistencePort, times(1)).save(homeModel);
    }

    @Test
    void testSave_CategoryNotFound() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(null);

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_LocationNotFound() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(LocationNotFoundException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_DuplicateAddress() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateAddressException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidPublicationDate() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPublicationInfo().setPublicationDate(LocalDate.now().minusDays(1)); // Fecha pasada
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidPublicationDateException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidActivePublicationDate() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPublicationInfo().setActivePublicationDate(LocalDate.now().minusDays(2)); // Fecha activa antes de publicación
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidPublicationDateException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_EmptyAddress() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getBasicInfo().setAddress("");
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));

        // Act & Assert
        assertThrows(EmptyAddressException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidNumberRooms() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPropertyDetails().setNumberOfRooms(0);
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidNumberException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidNumberBathrooms() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPropertyDetails().setNumberOfBathrooms(0);
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidNumberException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidName() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getBasicInfo().setName("");
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidPrice() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getBasicInfo().setPrice(0.0);
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_InvalidDescription() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPropertyDetails().setDescription("");
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testActivateScheduledPublications_Success() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPublicationInfo().setPublicationStatus(HomePublicationInfoModel.PublicationStatus.PUBLICATION_PAUSED);
        List<HomeModel> homesToActivate = Collections.singletonList(homeModel);

        when(homePersistencePort.findHomesToActivate(any(LocalDate.class), eq(HomePublicationInfoModel.PublicationStatus.PUBLICATION_PAUSED))).thenReturn(homesToActivate);

        // Act
        homeUseCase.activateScheduledPublications();

        // Assert
        assertEquals(HomePublicationInfoModel.PublicationStatus.PUBLISHED, homeModel.getPublicationInfo().getPublicationStatus());
        verify(homePersistencePort, times(1)).saveAll(homesToActivate);
    }

    @Test
    void testSave_NullNumberOfRooms() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPropertyDetails().setNumberOfRooms(null);
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidNumberException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }

    @Test
    void testSave_NullNumberOfBathrooms() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        homeModel.getPropertyDetails().setNumberOfBathrooms(null);
        CategoryModel category = CategoryModelFactory.createDefaultCategoryModel();
        LocationModel location = LocationModelFactory.createDefaultLocationModel();

        when(categoryPersistencePort.getCategoryByName(homeModel.getPropertyDetails().getCategory().getName())).thenReturn(category);
        when(locationPersistencePort.findById(homeModel.getBasicInfo().getLocation().getId())).thenReturn(java.util.Optional.of(location));
        when(homePersistencePort.existsAddress(homeModel.getBasicInfo().getAddress())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidNumberException.class, () -> homeUseCase.save(homeModel));
        verify(homePersistencePort, never()).save(homeModel);
    }
    @Test
    void testValidateAndSetPublicationStatus_EqualDates() {
        // Arrange
        HomeModel homeModel = HomeModelFactory.createDefaultHomeModel();
        LocalDate today = LocalDate.now();
        homeModel.getPublicationInfo().setPublicationDate(today);
        homeModel.getPublicationInfo().setActivePublicationDate(today);

        // Act
        homeUseCase.validateAndSetPublicationStatus(homeModel);

        // Assert
        assertEquals(HomePublicationInfoModel.PublicationStatus.PUBLISHED, homeModel.getPublicationInfo().getPublicationStatus());
    }
    @Test
    void testLocationExists_NullLocationModel() {
        // Arrange
        LocationModel locationModel = null;

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.locationExists(locationModel));
        verify(locationPersistencePort, never()).findById(anyLong());
    }

    @Test
    void testLocationExists_NullLocationId() {
        // Arrange
        LocationModel locationModel = LocationModelFactory.createDefaultLocationModel();
        locationModel.setId(null);

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.locationExists(locationModel));
        verify(locationPersistencePort, never()).findById(anyLong());
    }

    @Test
    void testFindHomesByFilters_InvalidSortBy() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createDefaultHomeQueryModel();
        String sortBy = "invalidSortBy";

        // Act & Assert
        assertThrows(InvalidSortByException.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, sortBy, "ASC"));
    }

    @Test
    void testFindHomesByFilters_MinRoomsNegative() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createHomeQueryModelWithMinRooms(-1);

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC"));
    }

    @Test
    void testFindHomesByFilters_MaxRoomsNegative() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createHomeQueryModelWithMaxRooms(-1);

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC"));
    }

    @Test
    void testFindHomesByFilters_MinBathroomsNegative() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createHomeQueryModelWithMinBathrooms(-1);

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC"));
    }

    @Test
    void testFindHomesByFilters_MaxBathroomsNegative() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createHomeQueryModelWithMaxBathrooms(-1);

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC"));
    }

    @Test
    void testFindHomesByFilters_MinPriceNegative() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createHomeQueryModelWithMinPrice(BigDecimal.valueOf(-1));

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC"));
    }

    @Test
    void testFindHomesByFilters_MaxPriceNegative() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createHomeQueryModelWithMaxPrice(BigDecimal.valueOf(-1));

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC"));
    }

    @Test
    void testFindHomesByFilters_InvalidSortDirection() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createDefaultHomeQueryModel();
        String sortDirection = "INVALID";

        // Act & Assert
        assertThrows(InvalidParameters.class, () -> homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", sortDirection));
    }

    @Test
    void testFindHomesByFilters_Success() {
        // Arrange
        HomeQueryModel queryModel = HomeQueryModelFactory.createDefaultHomeQueryModel();
        Pagination<HomeModel> expectedPagination = new Pagination<>(); // Puedes ajustar esto según tus necesidades
        when(homePersistencePort.findHomesByFilters(queryModel, 0, 10, "price", "ASC")).thenReturn(expectedPagination);

        // Act
        Pagination<HomeModel> result = homeUseCase.findHomesByFilters(queryModel, 0, 10, "price", "ASC");

        // Assert
        assertEquals(expectedPagination, result);
    }


}





