package com.pragma.hogar360.serviceshome.domain.ports.in;

import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import java.util.Optional;

/**
 * Port interface defining the contract for department-related domain services.
 * This interface provides methods for creating and retrieving department domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface DepartmentServicePort {

    /**
     * Creates a new department domain model.
     *
     * @param department The department domain model to create.
     * @return The created department domain model.
     */
    DepartmentModel createDepartment(DepartmentModel department);

    /**
     * Retrieves a department domain model by its name.
     *
     * @param name The name of the department to retrieve.
     * @return An {@link Optional} containing the department domain model, or an empty {@link Optional} if not found.
     */
    Optional<DepartmentModel> getDepartmentByName(String name);
}