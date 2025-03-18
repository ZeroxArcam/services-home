package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;
import com.pragma.hogar360.serviceshome.application.mappers.LocationsDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.LocationService;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateLocationException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Implementation of the {@link LocationService} interface, providing services for managing locations.
 * This class handles the creation of new locations, validating the existence of cities and departments.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
 * @since [17/3/2025]
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImplementation implements LocationService {

    private final CityPersistencePort cityPersistencePort;
    private final DepartmentPersistencePort departmentPersistencePort;
    private final LocationPersistencePort locationPersistencePort;
    private final LocationsDtoMapper locationsDtoMapper;

    /**
     * Creates a new location based on the provided request.
     */
    @Override
    public SaveLocationResponse createLocation(SaveLocationRequest request) {
        CityModel cityModel = cityPersistencePort.findByName(request.cityName())
                .orElseThrow(() -> new CityNotFoundException(Constants.NOT_FOUMD_CITY_RESPONSE_MESSAGE));

        DepartmentModel departmentModel = departmentPersistencePort.findByName(request.departmentName())
                .orElseThrow(() -> new DepartmentNotFoundException(Constants.NOT_FOUMD_DEPARTMENT_RESPONSE_MESSAGE + request.departmentName()));

        if (locationPersistencePort.existsByCityAndDepartment(request.cityName(), request.departmentName())) {
            throw new DuplicateLocationException(Constants.DUPLICATE_DATA);
        }

        LocationModel locationModel = new LocationModel(null, cityModel, departmentModel);
        locationPersistencePort.saveLocation(locationModel);

        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE);
    }

    /**
     * Retrieves a paginated list of locations with optional filtering.
     */
    @Override
    public PagedLocationResponse getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text) {
        Validation.ValidatePageAndSize(page,size);
        Pagination<LocationModel> locationPage = locationPersistencePort.getLocations(page, size, sortBy, sortDirection, text);

        List<LocationResponse> locationResponses = locationPage.getItems().stream()
                .map(locationsDtoMapper::modelToResponse)
                .toList();


        return new PagedLocationResponse(
                locationResponses,
                locationPage.getTotalElements(),
                locationPage.getTotalPages(),
                locationPage.getPageNumber(),
                locationPage.getPageSize()
        );
    }
//    /**
//     * Finds a location by city or department name.
//     */
//    @Override
//    public Optional<LocationResponse> getLocationByName(String name) {
//        return locationPersistencePort.findByName(name)
//                .map(locationsDtoMapper::modelToResponse);
//    }

}
