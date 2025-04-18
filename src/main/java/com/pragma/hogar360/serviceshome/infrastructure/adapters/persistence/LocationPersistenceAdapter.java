package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Auxiliary;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.LocationEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocationPersistenceAdapter implements LocationPersistencePort {

    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;

    @Override
    public void save(LocationModel locationModel) {
        locationRepository.save(locationEntityMapper.toEntity(locationModel));
    }

    @Override
    public boolean existsByCityId(Long id) {
        return locationRepository.existsByCityId(id);
    }

    @Override
    public boolean existsByNeighborhood(String neighborhood){
        return locationRepository.existsByNeighborhood(neighborhood);
    }
    @Override
    public Optional<LocationModel> findById(Long id) {
        return locationRepository.findById(id).map(locationEntityMapper::toModel);
    }

    @Override
    public Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text) {
        Sort sort = Auxiliary.createSort(sortBy, sortDirection);
        Pageable pageable = PageRequest.of(page, size, sort);
        log.info("Fetching locations from DB - Page: {}, Size: {}, SortBy: {}, SortDirection: {}, Text: '{}'",
                page, size, sortBy, sortDirection, text);
        Page<LocationEntity> locationPage = locationRepository.findByCityNameOrDepartmentNameContainingIgnoreCase(text, pageable);
        log.info("Locations fetched. Total Elements: {}, Total Pages: {}",
                locationPage.getTotalElements(), locationPage.getTotalPages());
        Pagination<LocationModel> result = locationEntityMapper.locationEntityPageToLocationModelPagination(locationPage);
        log.info("Mapped Locations: {}", result.getItems());
        log.info("Pagination mapped successfully. Returning response...");
        return result;
    }
}