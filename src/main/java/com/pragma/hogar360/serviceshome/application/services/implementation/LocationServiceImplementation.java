package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;
import com.pragma.hogar360.serviceshome.application.mappers.LocationsDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.LocationService;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.LocationServicePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
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

    private final LocationsDtoMapper locationsDtoMapper;
    private final LocationServicePort locationServicePort;
    /**
     * Creates a new location based on the provided request.
     */
    @Override
    public SaveLocationResponse createLocation(SaveLocationRequest request) {
        var locationModel = locationsDtoMapper.requestToModel(request);
        locationServicePort.createLocation(locationModel);
        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE);
    }
    /**
     * Retrieves a paginated list of locations with optional filtering.
     */
    @Override
    public PagedLocationResponse getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text) {
        Pagination<LocationModel> locationPagination = locationServicePort.getLocations(page, size, sortBy, sortDirection, text);
        List<LocationResponse> locationResponses = locationPagination.getItems().stream()
                .map(locationsDtoMapper::modelToResponse)
                .toList();
        return new PagedLocationResponse(
                locationResponses,
                locationPagination.getTotalElements(),
                locationPagination.getTotalPages(),
                locationPagination.getPageNumber(),
                locationPagination.getPageSize()
        );
    }

}
