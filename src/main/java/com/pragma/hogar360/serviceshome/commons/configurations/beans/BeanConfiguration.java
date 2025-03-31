package com.pragma.hogar360.serviceshome.commons.configurations.beans;

import com.pragma.hogar360.serviceshome.domain.ports.in.*;
import com.pragma.hogar360.serviceshome.domain.ports.out.*;
import com.pragma.hogar360.serviceshome.domain.usecases.*;
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.*;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.*;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.*;
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
    private final CityRepository cityRepository; //Inyecta CityRepository
    private final CityEntityMapper cityEntityMapper; //Inyecta CityEntityMapper
    private final DepartmentRepository departmentRepository; //Inyecta DepartmentRepository
    private final DepartmentEntityMapper departmentEntityMapper; //Inyecta DepartmentEntityMapper
    private final LocationRepository locationRepository; // Añade LocationRepository
    private final LocationEntityMapper locationEntityMapper; // Añade LocationEntityMapper
    private final HomeRepository homeRepository;
    private final HomeEntityMapper homeEntityMapper;

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

    @Bean
    public DepartmentPersistencePort departmentPersistencePort() {
        return new DepartmentPersistenceAdapter(departmentRepository, departmentEntityMapper);
    }

    @Bean
    public DepartmentServicePort departmentServicePort() {
        return new DepartmentUseCase(departmentPersistencePort());
    }

    @Bean
    public LocationServicePort locationServicePort(LocationPersistencePort locationPersistencePort, CityPersistencePort cityPersistencePort) {
        return new LocationUseCase(locationPersistencePort, cityPersistencePort);
    }

    @Bean
    public LocationPersistencePort locationPersistencePort(LocationRepository locationRepository,
                                                           LocationEntityMapper locationEntityMapper) {
        return new LocationPersistenceAdapter(locationRepository, locationEntityMapper);
    }

    @Bean
    public HomeServicePort homeServicePort(HomePersistencePort homePersistencePort, CategoryPersistencePort categoryPersistencePort, LocationPersistencePort locationPersistencePort) {
        return new HomeUseCase(homePersistencePort, categoryPersistencePort, locationPersistencePort);
    }

    @Bean
    public HomePersistencePort homePersistencePort() {
        return new HomePersistenceAdapter(homeRepository, homeEntityMapper);
    }

    @Bean
    public HomeUseCase homeUseCase(HomePersistencePort homePersistencePort, CategoryPersistencePort categoryPersistencePort, LocationPersistencePort locationPersistencePort) {
        return new HomeUseCase(homePersistencePort, categoryPersistencePort, locationPersistencePort);
    }
}