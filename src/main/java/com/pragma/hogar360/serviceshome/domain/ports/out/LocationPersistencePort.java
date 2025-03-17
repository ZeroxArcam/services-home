package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import java.util.Optional;

/**
 * Port interface defining the contract for location persistence operations.
 * This interface provides methods for saving, checking existence, and finding location domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface LocationPersistencePort {

    /**
     * Saves a location domain model.
     *
     * @param location The location domain model to save.
     * @return The saved location domain model.
     */
    LocationModel saveLocation(LocationModel location);

    /**
     * Checks if a location with the given city and department names exists.
     *
     * @param cityName       The name of the city to check.
     * @param departmentName The name of the department to check.
     * @return {@code true} if a location with the given city and department exists, {@code false} otherwise.
     */
    boolean existsByCityAndDepartment(String cityName, String departmentName);

    /**
     * Finds a location domain model by city and department names.
     *
     * @param cityName       The name of the city to find.
     * @param departmentName The name of the department to find.
     * @return An {@link Optional} containing the location domain model, or an empty {@link Optional} if not found.
     */
    Optional<LocationModel> findByCityNameAndDepartmentName(String cityName, String departmentName);
}