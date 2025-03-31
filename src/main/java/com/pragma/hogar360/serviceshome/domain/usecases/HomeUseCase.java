package com.pragma.hogar360.serviceshome.domain.usecases;
import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
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
}