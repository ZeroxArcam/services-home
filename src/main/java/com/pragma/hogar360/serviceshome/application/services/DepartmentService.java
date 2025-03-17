package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveDepartmentRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.DepartmentResponse;

import java.util.Optional;

/**
 * Interface defining the contract for department-related services.
 * This interface provides methods for creating and retrieving department information.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface DepartmentService {

    /**
     * Creates a new department based on the provided request.
     *
     * @param request The request containing the department details.
     * @return A {@link DepartmentResponse} representing the created department.
     */
    DepartmentResponse createDepartment(SaveDepartmentRequest request);

    /**
     * Retrieves a department by its name.
     *
     * @param name The name of the department to retrieve.
     * @return An {@link Optional} containing the {@link DepartmentResponse} if found, or an empty {@link Optional} if not.
     */
    Optional<DepartmentResponse> getDepartmentByName(String name);
}