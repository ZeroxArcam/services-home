package com.pragma.hogar360.serviceshome.domain.ports.in;

import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;


import java.util.Optional;

/**
 * Port interface defining the contract for location-related domain services.
 * This interface provides methods for creating and retrieving location domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
 * @since [16/3/2025]
 */
public interface LocationServicePort {

    /**
     * Creates a new location domain model.
     *
     * @param location The location domain model to create.
     * @return The created location domain model.
     */
    LocationModel createLocation(LocationModel location);

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
    Pagination<LocationModel> getLocations(Integer page, Integer size, String sortBy, String sortDirection, String text);

//    /**
//     * Finds a location by either city or department name.
//     *
//     * @param name The name of the city or department to search.
//     * @return An {@link Optional} containing the location domain model, or an empty {@link Optional} if not found.
//     */
//    Optional<LocationModel> getByName(String name);
}
