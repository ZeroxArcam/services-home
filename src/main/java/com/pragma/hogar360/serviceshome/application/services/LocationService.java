package com.pragma.hogar360.serviceshome.application.services;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;

import java.util.Optional;

public interface LocationService {
    SaveLocationResponse createLocation(SaveLocationRequest request);
    PagedLocationResponse getLocations(Integer page, Integer size, String sortBy, String sortDirection , String text);
}