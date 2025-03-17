package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for Department entity operations using JPA.
 * Extends {@link JpaRepository} to provide standard database operations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    /**
     * Finds a DepartmentEntity by its name.
     *
     * @param name The name of the department to find.
     * @return An {@link Optional} containing the DepartmentEntity, or an empty {@link Optional} if not found.
     */
    Optional<DepartmentEntity> findByName(String name);

    /**
     * Checks if a DepartmentEntity with the given name exists.
     *
     * @param name The name of the department to check.
     * @return {@code true} if a DepartmentEntity with the name exists, {@code false} otherwise.
     */
    boolean existsByName(String name);
}