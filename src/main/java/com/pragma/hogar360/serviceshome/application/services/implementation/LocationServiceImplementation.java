package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;
import com.pragma.hogar360.serviceshome.application.services.LocationService;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateLocationException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link LocationService} interface, providing services for managing locations.
 * This class handles the creation of new locations, validating the existence of cities and departments.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImplementation implements LocationService {

    /**
     * Persistence port for city operations.
     */
    private final CityPersistencePort cityPersistencePort;

    /**
     * Persistence port for department operations.
     */
    private final DepartmentPersistencePort departmentPersistencePort;

    /**
     * Persistence port for location operations.
     */
    private final LocationPersistencePort locationPersistencePort;

    /**
     * Creates a new location based on the provided request.
     *
     * @param request The request containing the location details.
     * @return A {@link SaveLocationResponse} indicating the success of the operation.
     * @throws CityNotFoundException If the city does not exist.
     * @throws DepartmentNotFoundException If the department does not exist.
     * @throws DuplicateLocationException If a location with the same city and department already exists.
     */
    @Override
    public SaveLocationResponse createLocation(SaveLocationRequest request) {
        CityModel cityModel = cityPersistencePort.findByName(request.cityName())
                .orElseThrow(() -> new CityNotFoundException(Constants.NOT_FOUMD_CITY_RESPONSE_MESSAGE));

        DepartmentModel departmentModel = departmentPersistencePort.findByName(request.departmentName())
                .orElseThrow(() -> new DepartmentNotFoundException(Constants.NOT_FOUMD_DEPARTMENT_RESPONSE_MESSAGE + request.departmentName()));

        if (locationPersistencePort.existsByCityAndDepartment(request.cityName(), request.departmentName())) {
            LocationModel locationModel = new LocationModel(null, cityModel, departmentModel);
            LocationModel savedLocation = locationPersistencePort.saveLocation(locationModel);
        } else {
            throw new DuplicateLocationException(Constants.DUPLICATE_DATA);
        }
        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE);
    }
}