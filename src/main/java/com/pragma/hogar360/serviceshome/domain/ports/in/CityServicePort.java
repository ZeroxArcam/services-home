package com.pragma.hogar360.serviceshome.domain.ports.in;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import java.util.Optional;

/**
 * Port interface defining the contract for city-related domain services.
 * This interface provides methods for creating and retrieving city domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface CityServicePort {

    /**
     * Creates a new city domain model.
     *
     * @param city The city domain model to create.
     * @return The created city domain model.
     */
    CityModel createCity(CityModel city);

    /**
     * Retrieves a city domain model by its name.
     *
     * @param name The name of the city to retrieve.
     * @return An {@link Optional} containing the city domain model, or an empty {@link Optional} if not found.
     */
    Optional<CityModel> getCityByName(String name);
}