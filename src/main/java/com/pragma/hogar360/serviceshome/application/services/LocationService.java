package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveLocationResponse;

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
}