package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveDepartmentRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.DepartmentResponse;
import com.pragma.hogar360.serviceshome.application.mappers.DepartmentDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.DepartmentService;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.DepartmentServicePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link DepartmentService} interface, providing services for managing departments.
 * This class handles the creation and retrieval of department information.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Service
public class DepartmentServiceImplementation implements DepartmentService {

    /**
     * Port for domain-level department service operations.
     */
    private final DepartmentServicePort departmentServicePort;

    /**
     * Mapper for converting between department DTOs and domain models.
     */
    private final DepartmentDtoMapper departmentDtoMapper;

    /**
     * Constructor for DepartmentServiceImplementation.
     *
     * @param departmentServicePort Port for domain-level department service operations.
     * @param departmentDtoMapper Mapper for converting between department DTOs and domain models.
     */
    public DepartmentServiceImplementation(DepartmentServicePort departmentServicePort, DepartmentDtoMapper departmentDtoMapper) {
        this.departmentServicePort = departmentServicePort;
        this.departmentDtoMapper = departmentDtoMapper;
    }

    /**
     * Creates a new department based on the provided request.
     *
     * @param request The request containing the department details.
     * @return A {@link DepartmentResponse} representing the created department.
     */
    @Override
    public DepartmentResponse createDepartment(SaveDepartmentRequest request) {
        DepartmentModel departmentModel = departmentDtoMapper.requestToModel(request);
        DepartmentModel savedDepartment = departmentServicePort.createDepartment(departmentModel);
        return departmentDtoMapper.modelToResponse(savedDepartment);
    }

    /**
     * Retrieves a department by its name.
     *
     * @param name The name of the department to retrieve.
     * @return An {@link Optional} containing the {@link DepartmentResponse} if found, or an empty {@link Optional} if not.
     */
    @Override
    public Optional<DepartmentResponse> getDepartmentByName(String name) {
        return departmentServicePort.getDepartmentByName(name)
                .map(departmentDtoMapper::modelToResponse);
    }
}