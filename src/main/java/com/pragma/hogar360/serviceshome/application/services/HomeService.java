package com.pragma.hogar360.serviceshome.application.services;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedHomeResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveHomeResponse;
import com.pragma.hogar360.serviceshome.domain.model.HomeQueryModel;

public interface HomeService {
    SaveHomeResponse save(SaveHomeRequest request);
    PagedHomeResponse findHomesByFilters(
            HomeQueryModel queryModel,
            Integer page,
            Integer size,
            String sortBy,
            String sortDirection
    );
}