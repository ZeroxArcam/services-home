package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

import java.util.Optional;

/**
 * Port interface defining the contract for location persistence operations.
 * This interface provides methods for saving, checking existence, and finding location domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
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

    /**
     * Retrieves a paginated list of locations with metadata.
     *
     * @param page          The page number (0-based).
     * @param size          The number of locations per page.
     * @param sortBy        "cityName" to order by city, "departmentName" to order by department.
     * @param sortDirection "ASC" for ascending order, "DESC" for descending order.
     * @param text          The search text for filtering locations (city or department).
     * @return A PagedLocationResponse DTO containing the list of locations and pagination metadata.
     */
    Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection , String text);

//    /**
//     * Finds a location by either city or department name.
//     *
//     * @param name The name of the city or department to search.
//     * @return An {@link Optional} containing the location domain model, or an empty {@link Optional} if not found.
//     */
//    Optional<LocationModel> findByName(String name);
}
