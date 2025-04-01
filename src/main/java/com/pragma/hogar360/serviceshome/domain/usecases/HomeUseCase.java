package com.pragma.hogar360.serviceshome.domain.usecases;
import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.*;
import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HomeUseCase implements HomeServicePort {

    private final HomePersistencePort homePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final LocationPersistencePort locationPersistencePort;

    public HomeUseCase(HomePersistencePort homePersistencePort, CategoryPersistencePort categoryPersistencePort, LocationPersistencePort locationPersistencePort) {
        this.homePersistencePort = homePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.locationPersistencePort = locationPersistencePort;
    }

    @Override
    public void save(HomeModel homeModel) {
        CategoryModel category=categoryExists(homeModel.getPropertyDetails().getCategory().getName());
        LocationModel location=locationExists(homeModel.getBasicInfo().getLocation());
        homeModel.getPropertyDetails().setCategory(category);
        homeModel.getBasicInfo().setLocation(location);
        validateAndSetPublicationStatus(homeModel);
        validateAddress(homeModel.getBasicInfo().getAddress());
        validateFields(homeModel);
        homePersistencePort.save(homeModel);
    }
    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new EmptyAddressException(DomainConstants.ADDRESS_NULL_OR_EMPTY);
        }
        if (homePersistencePort.existsAddress(address)) {
            throw new DuplicateAddressException(DomainConstants.ADDRESS_ALREADY_EXISTS);
        }
    }
    private void validateFields(HomeModel homeModel) {
        if(homeModel.getPropertyDetails().getNumberOfRooms()==null  || homeModel.getPropertyDetails().getNumberOfBathrooms()==null){
            throw new InvalidNumberException(DomainConstants.INVALID_NUMBER_OF_ROOMS);
        }
        if(homeModel.getPropertyDetails().getNumberOfRooms()<DomainConstants.NUMBER_OF_ROOM_INT  || homeModel.getPropertyDetails().getNumberOfBathrooms()<DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidNumberException(DomainConstants.INVALID_NUMBER_OF_ROOMS);
        }

        if(homeModel.getBasicInfo().getName()==null || homeModel.getBasicInfo().getName().trim().isEmpty() || homeModel.getBasicInfo().getPrice()==null || homeModel.getBasicInfo().getPrice()<DomainConstants.NUMBER_OF_ROOM_INT){
            throw new InvalidParameters(DomainConstants.INVALID_PARAMETERS);
        }
        if (homeModel.getPropertyDetails().getDescription()==null || homeModel.getPropertyDetails().getDescription().trim().isEmpty()) {
            throw new InvalidParameters(DomainConstants.INVALID_PARAMETERS);
        }
    }

    public CategoryModel categoryExists(String categoryName) {
        CategoryModel categoryModel = categoryPersistencePort.getCategoryByName(categoryName);
        if (categoryModel == null) {

            throw new CategoryNotFoundException(DomainConstants.NOT_FOUND);
        }
        return categoryModel;
    }

    public LocationModel locationExists(LocationModel locationModel) {
        if (locationModel == null || locationModel.getId() == null) {
            throw new InvalidParameters(DomainConstants.ID_LOCATION_HOME_INVALID);
        }
        return locationPersistencePort.findById(locationModel.getId())
                .orElseThrow(() -> new LocationNotFoundException(DomainConstants.NOT_FOUND));
    }

    public void validateAndSetPublicationStatus(HomeModel homeModel) {
        LocalDate publicationDate = homeModel.getPublicationInfo().getPublicationDate();
        LocalDate activePublicationDate = homeModel.getPublicationInfo().getActivePublicationDate();
        LocalDate currentDate = LocalDate.now();
        LocalDate maxAllowedDate = currentDate.plusDays(DomainConstants.MAX_ALLOWED_DATE);

        if (publicationDate.isBefore(currentDate) || publicationDate.isAfter(maxAllowedDate)) {
            throw new InvalidPublicationDateException(DomainConstants.PUBLICATION_DATE_PAST_ERROR);
        }
        if (activePublicationDate.isBefore(publicationDate) || activePublicationDate.isAfter(maxAllowedDate)) {
            throw new InvalidPublicationDateException(DomainConstants.ACTIVE_DATE_BEFORE_PUBLICATION_ERROR);
        }
        if (activePublicationDate.isEqual(publicationDate)) {
            homeModel.getPublicationInfo().setPublicationStatus(
                    HomePublicationInfoModel.PublicationStatus.PUBLISHED
            );
        } else {
            homeModel.getPublicationInfo().setPublicationStatus(
                    HomePublicationInfoModel.PublicationStatus.PUBLICATION_PAUSED
            );
        }
    }
    public void activateScheduledPublications() {
        LocalDate today = LocalDate.now();
        List<HomeModel> homesToActivate = homePersistencePort.findHomesToActivate(
                today,
                HomePublicationInfoModel.PublicationStatus.PUBLICATION_PAUSED
        );
        homesToActivate.forEach(home ->
                home.getPublicationInfo().setPublicationStatus(
                        HomePublicationInfoModel.PublicationStatus.PUBLISHED
                )
        );
        homePersistencePort.saveAll(homesToActivate);
    }
    @Override
    public Pagination<HomeModel> findHomesByFilters(
            HomeQueryModel queryModel,
            Integer page,
            Integer size,
            String sortBy,
            String sortDirection
    ) {
        if (!DomainConstants.VALID_SORT_BY_VALUES.contains(sortBy)) {
            throw new InvalidSortByException(String.format(DomainConstants.INVALID_SORT_BY_MESSAGE, sortBy));
        }

        if (queryModel.getMinRooms() != null && queryModel.getMinRooms() < DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidParameters(DomainConstants.MIN_ROOMS_NEGATIVE_MESSAGE);
        }
        if (queryModel.getMaxRooms() != null && queryModel.getMaxRooms() < DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidParameters(DomainConstants.MAX_ROOMS_NEGATIVE_MESSAGE);
        }
        if (queryModel.getMinBathrooms() != null && queryModel.getMinBathrooms() < DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidParameters(DomainConstants.MIN_BATHROOMS_NEGATIVE_MESSAGE);
        }
        if (queryModel.getMaxBathrooms() != null && queryModel.getMaxBathrooms() < DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidParameters(DomainConstants.MAX_BATHROOMS_NEGATIVE_MESSAGE);
        }
        if (queryModel.getMinPrice() != null && queryModel.getMinPrice().compareTo(BigDecimal.ZERO) < DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidParameters(DomainConstants.MIN_PRICE_NEGATIVE_MESSAGE);
        }
        if (queryModel.getMaxPrice() != null && queryModel.getMaxPrice().compareTo(BigDecimal.ZERO) < DomainConstants.NUMBER_OF_ROOM_INT) {
            throw new InvalidParameters(DomainConstants.MAX_PRICE_NEGATIVE_MESSAGE);
        }
        if (!sortDirection.equalsIgnoreCase(DomainConstants.SORT_ASC) && !sortDirection.equalsIgnoreCase(DomainConstants.SORT_DESC)) {
            throw new InvalidParameters(String.format(DomainConstants.INVALID_SORT_DIRECTION_MESSAGE, sortDirection));
        }
        return homePersistencePort.findHomesByFilters(queryModel, page, size, sortBy, sortDirection);
    }
}