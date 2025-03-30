package com.pragma.hogar360.serviceshome.domain.ports.out;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

public interface LocationPersistencePort {

    void save(LocationModel location);
    boolean existsByCityId(Long id);
    boolean existsByNeighborhood(String neighborhood);
    Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection , String text);
}
