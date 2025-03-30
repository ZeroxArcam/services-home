package com.pragma.hogar360.serviceshome.application.services.implementation;
import com.pragma.hogar360.serviceshome.application.appconstants.Constants;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;
import com.pragma.hogar360.serviceshome.application.mappers.LocationsDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.LocationService;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.LocationServicePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImplementation implements LocationService {

    private final LocationsDtoMapper locationsDtoMapper;
    private final LocationServicePort locationServicePort;

    @Override
    public SaveLocationResponse createLocation(SaveLocationRequest request) {
        locationServicePort.save(locationsDtoMapper.requestToModel(request));
        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE);
    }

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
