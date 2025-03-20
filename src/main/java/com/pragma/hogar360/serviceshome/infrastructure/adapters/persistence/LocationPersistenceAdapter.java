package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.commons.configurations.utils.Auxiliary;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateLocationException;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.LocationEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CityRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.DepartmentRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.Optional;

/**
 * Adapter class for location persistence operations using JPA.
 * Implements the {@link LocationPersistencePort} interface.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
 * @since [16/3/2025]
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocationPersistenceAdapter implements LocationPersistencePort {

    /**
     * Repository for location entity operations.
     */
    private final LocationRepository locationRepository;

    /**
     * Repository for city entity operations.
     */
    private final CityRepository cityRepository;

    /**
     * Repository for department entity operations.
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Mapper for converting between location domain models and entities.
     */
    private final LocationEntityMapper locationEntityMapper;

    /**
     * Saves a location domain model to the database.
     *
     * @param locationModel The location domain model to save.
     * @return The saved location domain model.
     * @throws DuplicateLocationException If a location with the same city and department already exists.
     */
    @Override
    public LocationModel saveLocation(LocationModel locationModel) {
        log.info("Saving location with City: {} and Department: {}",
                locationModel.getCity().getName(),
                locationModel.getDepartment().getName());

        Optional<CityEntity> cityEntityOpt = cityRepository.findByName(locationModel.getCity().getName());
        Optional<DepartmentEntity> departmentEntityOpt = departmentRepository.findByName(locationModel.getDepartment().getName());

        try {
            LocationEntity locationEntity = locationEntityMapper.toEntity(locationModel, cityEntityOpt.get(), departmentEntityOpt.get());
            LocationEntity savedEntity = locationRepository.save(locationEntity);
            log.info("Location saved successfully with ID: {}", savedEntity.getId());
            return locationEntityMapper.toModel(savedEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateLocationException(Constants.DUPLICATE_DATA);
        }
    }

    /**
     * Checks if a location with the given city and department names exists in the database.
     *
     * @param cityName       The name of the city to check.
     * @param departmentName The name of the department to check.
     * @return {@code true} if a location with the given city and department exists, {@code false} otherwise.
     */
    @Override
    public boolean existsByCityAndDepartment(String cityName, String departmentName) {
        return locationRepository.existsByCityNameAndDepartmentName(cityName, departmentName);
    }

    /**
     * Finds a location domain model by city and department names in the database.
     *
     * @param cityName       The name of the city to find.
     * @param departmentName The name of the department to find.
     * @return An {@link Optional} containing the location domain model, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<LocationModel> findByCityNameAndDepartmentName(String cityName, String departmentName) {
        Optional<LocationEntity> locationEntity = locationRepository.findByCityNameAndDepartmentName(cityName, departmentName);
        return locationEntity.map(locationEntityMapper::toModel);
    }

    @Override
    public Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text) {

        Sort sort = Auxiliary.createSort(sortBy, sortDirection);
        Pageable pageable = PageRequest.of(page, size, sort);


        log.info("Fetching locations from DB - Page: {}, Size: {}, SortBy: {}, SortDirection: {}, Text: '{}'",
                page, size, sortBy, sortDirection, text);

        Page<LocationEntity> locationPage;

        if (sortBy.equalsIgnoreCase("cityName")) {
            locationPage = locationRepository.findByCityNameContainingIgnoreCase(text, pageable);
        } else if (sortBy.equalsIgnoreCase("departmentName")) {
            log.info("sortBy:{}",sortBy);
            locationPage = locationRepository.findByDepartment_NameContainingIgnoreCase(text, pageable);
        } else {
            locationPage = locationRepository.findByCityNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(text, text, pageable);
        }

        log.info("Locations fetched. Total Elements: {}, Total Pages: {}",
                locationPage.getTotalElements(), locationPage.getTotalPages());

        Pagination<LocationModel> result = locationEntityMapper.locationEntityPageToLocationModelPagination(locationPage);
        log.info("Mapped Locations: {}", result.getItems());

        log.info("Pagination mapped successfully. Returning response...");
        return result;
    }

}