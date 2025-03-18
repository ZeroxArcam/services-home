package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.LocationServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

/**
 * Use case implementation for location-related domain operations.
 * This class handles the creation of location domain models, including validation for city, department, and duplicate locations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
 * @since [16/3/2025]
 */
public class LocationUseCase implements LocationServicePort {

    /**
     * Persistence port for location operations.
     */
    private final LocationPersistencePort locationPersistencePort;

    /**
     * Persistence port for city operations.
     */
    private final CityPersistencePort cityPersistencePort;

    /**
     * Persistence port for department operations.
     */
    private final DepartmentPersistencePort departmentPersistencePort;

    /**
     * Constructs a new LocationUseCase instance.
     *
     * @param locationPersistencePort Persistence port for location operations.
     * @param cityPersistencePort     Persistence port for city operations.
     * @param departmentPersistencePort Persistence port for department operations.
     */
    public LocationUseCase(LocationPersistencePort locationPersistencePort,
                           CityPersistencePort cityPersistencePort,
                           DepartmentPersistencePort departmentPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
        this.departmentPersistencePort = departmentPersistencePort;
    }

    /**
     * Creates a new location domain model, validating for city, department, and duplicate locations.
     *
     * @param location The location domain model to create.
     * @return The created location domain model.
     * @throws CityNotFoundException    If the city does not exist.
     * @throws DepartmentNotFoundException If the department does not exist.
     * @throws DuplicateLocationException If a location with the same city and department already exists.
     */
    @Override
    public LocationModel createLocation(LocationModel location) {
        validateCityExists(location.getCityName());
        validateDepartmentExists(location.getDepartmentName());
        validateLocationNotExists(location.getCityName(), location.getDepartmentName());

        return locationPersistencePort.saveLocation(location);
    }

    /**
     * Validates if the city exists.
     *
     * @param cityName The name of the city to validate.
     * @throws CityNotFoundException If the city does not exist.
     */
    private void validateCityExists(String cityName) {
        if (!cityPersistencePort.existsByName(cityName)) {
            throw new CityNotFoundException(DomainConstants.NOT_FOUND);
        }
    }

    /**
     * Validates if the department exists.
     *
     * @param departmentName The name of the department to validate.
     * @throws DepartmentNotFoundException If the department does not exist.
     */
    private void validateDepartmentExists(String departmentName) {
        if (!departmentPersistencePort.existsByName(departmentName)) {
            throw new DepartmentNotFoundException(DomainConstants.NOT_FOUND);
        }
    }

    /**
     * Validates if the location does not already exist.
     *
     * @param cityName       The name of the city to validate.
     * @param departmentName The name of the department to validate.
     * @throws DuplicateLocationException If the location already exists.
     */
    private void validateLocationNotExists(String cityName, String departmentName) {
        if (locationPersistencePort.existsByCityAndDepartment(cityName, departmentName)) {
            throw new DuplicateLocationException(DomainConstants.ALREADY_EXISTS);
        }
    }

    /**
     * Retrieves a paginated list of CategoryModels.
     *
     * @param page    The page number (0-based).
     * @param size    The number of categories per page.
     * @param sortBy  True for ascending order, false for descending order.
     * @return A list of CategoryModels representing the requested page.
     */
    @Override
    public Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text){

        Validation.ValidatePageAndSize(page,size);
        if (!sortBy.equalsIgnoreCase("cityName") && !sortBy.equalsIgnoreCase("departmentName")) {
            throw new IllegalArgumentException("Invalid sortBy parameter: " + sortBy);
        }
        if (!sortDirection.equalsIgnoreCase("ASC") && !sortDirection.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Invalid sortDirection parameter: " + sortDirection);
        }

        String normalizedText = Validation.normalizeText(text);
        return locationPersistencePort.getLocations(page, size, sortBy,sortDirection,normalizedText);
    }



}