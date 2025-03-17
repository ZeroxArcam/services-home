package com.pragma.hogar360.serviceshome.commons.configurations.beans;

import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.in.CityServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.in.DepartmentServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.usecases.CategoryUseCase;
import com.pragma.hogar360.serviceshome.domain.usecases.CityUseCase;
import com.pragma.hogar360.serviceshome.domain.usecases.DepartmentUseCase;
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.CityPersistenceAdapter;
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.DepartmentPersistenceAdapter;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CityEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.DepartmentEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.LocationEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CityRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.DepartmentRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pragma.hogar360.serviceshome.domain.ports.in.LocationServicePort; // Importa LocationServicePort
import com.pragma.hogar360.serviceshome.domain.usecases.LocationUseCase; // Importa LocationUseCase
import com.pragma.hogar360.serviceshome.domain.ports.out.LocationPersistencePort; // Importa LocationPersistencePort
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.LocationPersistenceAdapter; // Importa LocationPersistenceAdapter




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