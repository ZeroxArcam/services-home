package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.DepartmentEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Adapter class for department persistence operations using JPA.
 * Implements the {@link DepartmentPersistencePort} interface.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DepartmentPersistenceAdapter implements DepartmentPersistencePort {

    /**
     * Repository for department entity operations.
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Mapper for converting between department domain models and entities.
     */
    private final DepartmentEntityMapper departmentEntityMapper;

    /**
     * Saves a department domain model to the database.
     *
     * @param departmentModel The department domain model to save.
     * @return The saved department domain model.
     */
    @Override
    public DepartmentModel saveDepartment(DepartmentModel departmentModel) {
        log.info("Saving department: {}", departmentModel.getName());
        DepartmentEntity departmentEntity = departmentEntityMapper.toEntity(departmentModel);
        DepartmentEntity savedEntity = departmentRepository.save(departmentEntity);
        log.info("Department saved successfully: {}", savedEntity.getName());
        return departmentEntityMapper.toModel(savedEntity);
    }

    /**
     * Checks if a department with the given name exists in the database.
     *
     * @param name The name of the department to check.
     * @return {@code true} if a department with the name exists, {@code false} otherwise.
     */
    @Override
    public boolean existsByName(String name) {
        log.info("Checking if department exists by name: {}", name);
        boolean exists = departmentRepository.existsByName(name);
        log.info("Department exists: {}", exists);
        return exists;
    }

    /**
     * Finds a department domain model by its name in the database.
     *
     * @param name The name of the department to find.
     * @return An {@link Optional} containing the department domain model, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<DepartmentModel> findByName(String name) {
        log.info("Fetching department by name: {}", name);
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findByName(name);
        log.info("Department found: {}", departmentEntity.isPresent());
        return departmentEntity.map(departmentEntityMapper::toModel);
    }
}