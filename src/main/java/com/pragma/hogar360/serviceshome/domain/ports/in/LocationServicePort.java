package com.pragma.hogar360.serviceshome.domain.ports.in;

import com.pragma.hogar360.serviceshome.domain.model.LocationModel;

/**
 * Port interface defining the contract for location-related domain services.
 * This interface provides methods for creating location domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
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
}