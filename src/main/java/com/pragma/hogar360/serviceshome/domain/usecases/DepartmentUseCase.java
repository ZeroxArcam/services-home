package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.DepartmentServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;
import java.util.Optional;

/**
 * Use case implementation for department-related domain operations.
 * This class handles the creation and retrieval of department domain models, including validation for duplicate department names.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public class DepartmentUseCase implements DepartmentServicePort {

    /**
     * Persistence port for department operations.
     */
    private final DepartmentPersistencePort departmentPersistencePort;

    /**
     * Constructs a new DepartmentUseCase instance.
     *
     * @param departmentPersistencePort Persistence port for department operations.
     */
    public DepartmentUseCase(DepartmentPersistencePort departmentPersistencePort) {
        this.departmentPersistencePort = departmentPersistencePort;
    }

    /**
     * Creates a new department domain model, validating for duplicate department names.
     *
     * @param department The department domain model to create.
     * @return The created department domain model.
     * @throws DuplicateDepartmentNameException If a department with the same name already exists.
     */
    @Override
    public DepartmentModel createDepartment(DepartmentModel department) {
        Validation.validateName(department.getName());
        Validation.validationCityDepartmentDescription(department.getDescription());

        if (departmentPersistencePort.existsByName(department.getName())) {
            throw new DuplicateDepartmentNameException(DomainConstants.ALREADY_EXISTS);
        }
        return departmentPersistencePort.saveDepartment(department);
    }

    /**
     * Retrieves a department domain model by its name.
     *
     * @param name The name of the department to retrieve.
     * @return An {@link Optional} containing the department domain model, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<DepartmentModel> getDepartmentByName(String name) {
        return departmentPersistencePort.findByName(name);
    }
}