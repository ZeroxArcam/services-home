package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import java.time.LocalDate;
import java.util.Optional;
import java.time.temporal.ChronoUnit;

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
//    public HomeModel createHome(HomeModel homeModel) {

        CategoryModel category=categoryExists(homeModel.getPropertyDetails().getCategory().getName());
        LocationModel location=locationExists(homeModel.getBasicInfo().getLocation().getCityName(),homeModel.getBasicInfo().getLocation().getDepartmentName());
        homeModel.getPropertyDetails().setCategory(category);
        homeModel.getBasicInfo().setLocation(location);
        validatePublicationActive(homeModel.getPublicationInfo().getPublicationDate(), homeModel.getPublicationInfo().getActivePublicationDate());
        //return homePersistencePort.saveHome(homeModel);
        homePersistencePort.save(homeModel);
    }

    private CategoryModel categoryExists(String categoryName) {
        CategoryModel categoryModel = categoryPersistencePort.getCategoryByName(categoryName);
        if (categoryModel == null) {
            throw new CityNotFoundException(DomainConstants.NOT_FOUND);
        }
        return categoryModel;
    }

    private LocationModel locationExists(String locationCityName, String LocationDepartmentName) {
        Optional<LocationModel> locationModelOptional = locationPersistencePort.findByCityNameAndDepartmentName(locationCityName, LocationDepartmentName);
        if (locationModelOptional.isPresent()) {
            return locationModelOptional.get();
        } else {
            throw new CityNotFoundException(DomainConstants.NOT_FOUND);
        }
    }

    public void validatePublicationActive(LocalDate publicationDate, LocalDate activePublicationDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate maxActivePublicationDate = publicationDate.plus(30, ChronoUnit.DAYS);

        if (publicationDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("The publication date cannot be in the past");
        }

        if (activePublicationDate.isBefore(publicationDate)) {
            throw new IllegalArgumentException("The active publication date cannot be before the publication date");
        }

        if (activePublicationDate.isAfter(maxActivePublicationDate)) {
            throw new IllegalArgumentException("The active publication date cannot exceed 30 days from the publication date");
        }
    }


//    private HomeModel homeExists(HomeModel home){
//        if (!homePersistencePort.existById(home)){
//            throw new HomeAlreadyExistsException(DomainConstants.HOME_ALREADY_EXISTS);
//        }
//        return home;
//    }
}