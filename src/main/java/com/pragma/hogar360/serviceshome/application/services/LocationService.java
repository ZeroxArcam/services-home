package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;

import java.util.Optional;

/**
 * Interface defining the contract for location-related services.
 * This interface provides methods for creating location information.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface LocationService {

    /**
     * Creates a new location based on the provided request.
     *
     * @param request The request containing the location details.
     * @return A {@link SaveLocationResponse} indicating the success of the operation.
     */
    SaveLocationResponse createLocation(SaveLocationRequest request);

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
    PagedLocationResponse getLocations(Integer page, Integer size, String sortBy, String sortDirection , String text);

//    /**
//     * Retrieves a LocationResponse by its name.
//     *
//     * @param name The name of the city or department to search for.
//     * @return The LocationResponse object if found, or null if not found.
//     */
//    Optional<LocationResponse> getLocationByName(String name);

}