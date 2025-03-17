package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CityResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCityResponse;

import java.util.Optional;

/**
 * Interface defining the contract for city-related services.
 * This interface provides methods for creating and retrieving city information.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface CityService {

    /**
     * Creates a new city based on the provided request.
     *
     * @param request The request containing the city details.
     * @return A {@link SaveCityResponse} indicating the success of the operation.
     */
    SaveCityResponse createCity(SaveCityRequest request);

    /**
     * Retrieves a city by its name.
     *
     * @param name The name of the city to retrieve.
     * @return An {@link Optional} containing the {@link CityResponse} if found, or an empty {@link Optional} if not.
     */
    Optional<CityResponse> getCityByName(String name);
}