package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import java.util.Optional;

/**
 * Port interface defining the contract for department persistence operations.
 * This interface provides methods for saving, checking existence, and finding department domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface DepartmentPersistencePort {

    /**
     * Saves a department domain model.
     *
     * @param department The department domain model to save.
     * @return The saved department domain model.
     */
    DepartmentModel saveDepartment(DepartmentModel department);

    /**
     * Checks if a department with the given name exists.
     *
     * @param name The name of the department to check.
     * @return {@code true} if a department with the name exists, {@code false} otherwise.
     */
    boolean existsByName(String name);

    /**
     * Finds a department domain model by its name.
     *
     * @param name The name of the department to find.
     * @return An {@link Optional} containing the department domain model, or an empty {@link Optional} if not found.
     */
    Optional<DepartmentModel> findByName(String name);
}