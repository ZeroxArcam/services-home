package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CityServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

import java.util.Optional;

/**
 * Use case implementation for city-related domain operations.
 * This class handles the creation and retrieval of city domain models, including validation for duplicate city names.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public class CityUseCase implements CityServicePort {

    /**
     * Persistence port for city operations.
     */
    private final CityPersistencePort cityPersistencePort;

    /**
     * Persistence port for department operations (used for dependency injection, though not directly used in this use case).
     */
    private final DepartmentPersistencePort departmentPersistencePort;

    /**
     * Constructs a new CityUseCase instance.
     *
     * @param cityPersistencePort     Persistence port for city operations.
     * @param departmentPersistencePort Persistence port for department operations.
     */
    public CityUseCase(CityPersistencePort cityPersistencePort, DepartmentPersistencePort departmentPersistencePort) {
        this.cityPersistencePort = cityPersistencePort;
        this.departmentPersistencePort = departmentPersistencePort;
    }

    /**
     * Creates a new city domain model, validating for duplicate city names.
     *
     * @param city The city domain model to create.
     * @return The created city domain model.
     * @throws DuplicateDepartmentNameException If a city with the same name already exists.
     */
    @Override
    public CityModel createCity(CityModel city) {
        Validation.validateName(city.getName());
        Validation.validationCityDepartmentDescription(city.getDescription());

        if (cityPersistencePort.existsByName(city.getName())) {
            throw new DuplicateDepartmentNameException(DomainConstants.ALREADY_EXISTS);
        }
        return cityPersistencePort.saveCity(city);
    }

    /**
     * Retrieves a city domain model by its name.
     *
     * @param name The name of the city to retrieve.
     * @return An {@link Optional} containing the city domain model, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<CityModel> getCityByName(String name) {
        return cityPersistencePort.findByName(name);
    }
}