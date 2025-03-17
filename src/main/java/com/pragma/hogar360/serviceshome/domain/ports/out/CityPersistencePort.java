package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import java.util.Optional;

/**
 * Port interface defining the contract for city persistence operations.
 * This interface provides methods for saving, checking existence, and finding city domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface CityPersistencePort {

    /**
     * Saves a city domain model.
     *
     * @param city The city domain model to save.
     * @return The saved city domain model.
     */
    CityModel saveCity(CityModel city);

    /**
     * Checks if a city with the given name exists.
     *
     * @param name The name of the city to check.
     * @return {@code true} if a city with the name exists, {@code false} otherwise.
     */
    boolean existsByName(String name);

    /**
     * Finds a city domain model by its name.
     *
     * @param name The name of the city to find.
     * @return An {@link Optional} containing the city domain model, or an empty {@link Optional} if not found.
     */
    Optional<CityModel> findByName(String name);
}