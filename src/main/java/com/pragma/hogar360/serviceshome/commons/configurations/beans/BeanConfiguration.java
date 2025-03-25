package com.pragma.hogar360.serviceshome.commons.configurations.beans;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.*;
import com.pragma.hogar360.serviceshome.domain.ports.out.*;
import com.pragma.hogar360.serviceshome.domain.usecases.*;
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.*;
import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.*;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CityRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.DepartmentRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.HomeRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining Spring beans.
 * Provides beans for the domain and infrastructure layers.
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final CityRepository cityRepository; // Inyecta CityRepository
    private final CityEntityMapper cityEntityMapper; // Inyecta CityEntityMapper
    private final DepartmentRepository departmentRepository; // Inyecta DepartmentRepository
    private final DepartmentEntityMapper departmentEntityMapper; // Inyecta DepartmentEntityMapper
    private final LocationRepository locationRepository; // Añade LocationRepository
    private final LocationEntityMapper locationEntityMapper; // Añade LocationEntityMapper
    private final HomeRepository homeRepository;

    /**
     * Defines the CategoryServicePort bean.
     *
     * @return An instance of CategoryServicePort.
     */
    @Bean
    public CategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    /**
     * Defines the CategoryPersistencePort bean.
     *
     * @return An instance of CategoryPersistencePort.
     */
    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }

    /**
     * Defines the CityServicePort bean.
     *
     * @return An instance of CityServicePort.
     */
    @Bean
    public CityServicePort cityServicePort() {
        return new CityUseCase(cityPersistencePort(), departmentPersistencePort());
    }

    /**
     * Defines the CityPersistencePort bean.
     *
     * @return An instance of CityPersistencePort.
     */
    @Bean
    public CityPersistencePort cityPersistencePort() {
        return new CityPersistenceAdapter(cityRepository, cityEntityMapper);
    }

    /**
     * Defines the DepartmentPersistencePort bean.
     *
     * @return An instance of DepartmentPersistencePort.
     */
    @Bean
    public DepartmentPersistencePort departmentPersistencePort() {
        return new DepartmentPersistenceAdapter(departmentRepository, departmentEntityMapper);
    }

    /**
     * Defines the DepartmentServicePort bean.
     *
     * @return An instance of DepartmentServicePort.
     */
    @Bean
    public DepartmentServicePort departmentServicePort() {
        return new DepartmentUseCase(departmentPersistencePort());
    }

//    private final CategoryPersistencePort categoryPersistencePort;
//    private final LocationPersistencePort locationPersistencePort;
    @Bean
    public HomeServicePort homeServicePort(HomePersistencePort homePersistencePort, CategoryPersistencePort categoryPersistencePort, LocationPersistencePort locationPersistencePort) {
        return new HomeUseCase(homePersistencePort, categoryPersistencePort, locationPersistencePort);
    }

    @Bean
    public HomePersistencePort homePersistencePort(HomeRepository homeRepository, HomeEntityMapper homeEntityMapper) {
        return new HomePersistenceAdapter(homeRepository, homeEntityMapper);
    }

    @Bean
    public LocationServicePort locationServicePort(LocationPersistencePort locationPersistencePort,
                                                   CityPersistencePort cityPersistencePort,
                                                   DepartmentPersistencePort departmentPersistencePort) {
        return new LocationUseCase(locationPersistencePort, cityPersistencePort, departmentPersistencePort);
    }

    @Bean
    public LocationPersistencePort locationPersistencePort(LocationRepository locationRepository,
                                                           CityRepository cityRepository,
                                                           DepartmentRepository departmentRepository,
                                                           LocationEntityMapper locationEntityMapper) {
        return new LocationPersistenceAdapter(locationRepository, cityRepository, departmentRepository, locationEntityMapper);
    }
}