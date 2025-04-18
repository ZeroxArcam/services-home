package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.LocationServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

public class LocationUseCase implements LocationServicePort {

    private final LocationPersistencePort locationPersistencePort;
    private final CityPersistencePort cityPersistencePort;

    public LocationUseCase(LocationPersistencePort locationPersistencePort,
                           CityPersistencePort cityPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
    }

    @Override
    public void save(LocationModel location) {
        if (location.getCity() == null || location.getCity().getId() == null) {
            throw new CityNotFoundException(DomainConstants.NOT_FOUND);
        }
        if (location.getNeighborhood()==null || location.getNeighborhood().isEmpty()) {
            throw new IllegalArgumentException(DomainConstants.INVALID_PARAMETERS);
        }
        validateCityExists(location.getCity().getId());
        validateLocation(location.getNeighborhood(), location.getCity().getId());
        locationPersistencePort.save(location);
    }

    private void validateCityExists(Long id){
        if (!cityPersistencePort.existsById(id)) {
            throw new CityNotFoundException(DomainConstants.NOT_FOUND);
        }
    }
    private void validateLocation(String neighborhood,Long id){
        if (locationPersistencePort.existsByNeighborhood(neighborhood) && locationPersistencePort.existsByCityId(id) ) {
            throw new DuplicateLocationException(DomainConstants.ALREADY_EXISTS);
        }
    }

    @Override
    public Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text){
        Validation.validatePageAndSize(page,size);
        if (!sortBy.equalsIgnoreCase("cityName") && !sortBy.equalsIgnoreCase("departmentName")) {
            throw new InvalidParameters("Invalid sortBy parameter: " + sortBy);
        }
        if (!sortDirection.equalsIgnoreCase("ASC") && !sortDirection.equalsIgnoreCase("DESC")) {
            throw new InvalidParameters("Invalid sortDirection parameter: " + sortDirection);
        }
        return locationPersistencePort.getLocations(page, size, sortBy,sortDirection,text);
    }
}