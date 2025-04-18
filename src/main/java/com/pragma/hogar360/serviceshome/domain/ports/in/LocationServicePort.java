package com.pragma.hogar360.serviceshome.domain.ports.in;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

public interface LocationServicePort {

    void save(LocationModel location);
    Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text);

}
