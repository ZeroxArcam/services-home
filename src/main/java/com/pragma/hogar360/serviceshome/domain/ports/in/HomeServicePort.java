package com.pragma.hogar360.serviceshome.domain.ports.in;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeQueryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

public interface HomeServicePort {
    void save(HomeModel home);
    Pagination<HomeModel> findHomesByFilters(
            HomeQueryModel queryModel,
            Integer page,
            Integer size,
            String sortBy,
            String sortDirection
    );
    void activateScheduledPublications();

}