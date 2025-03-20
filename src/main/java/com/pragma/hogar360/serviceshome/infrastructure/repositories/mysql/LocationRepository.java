package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Repository interface for Location entity operations using JPA.
 * Extends {@link JpaRepository} to provide standard database operations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface LocationRepository extends JpaRepository<LocationEntity, Long>, JpaSpecificationExecutor<LocationEntity> {

    /**
     * Checks if a LocationEntity with the given city and department names exists.
     *
     * @param cityName       The name of the city to check.
     * @param departmentName The name of the department to check.
     * @return {@code true} if a LocationEntity with the given city and department exists, {@code false} otherwise.
     */
    boolean existsByCityNameAndDepartmentName(String cityName, String departmentName);

    /**
     * Finds a LocationEntity by city and department names.
     * Fetches associated CityEntity and DepartmentEntity eagerly using EntityGraph.
     *
     * @param cityName       The name of the city to find.
     * @param departmentName The name of the department to find.
     * @return An {@link Optional} containing the LocationEntity, or an empty {@link Optional} if not found.
     */
    @EntityGraph(attributePaths = {"city", "department"})
    Optional<LocationEntity> findByCityNameAndDepartmentName(String cityName, String departmentName);


    /**
     * Retrieves a paginated list of all Location entities.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Location entities.
     */
    Page<LocationEntity> findByCityNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(String cityName, String departmentName, Pageable pageable);
    Page<LocationEntity> findByCityNameContainingIgnoreCase(String cityName, Pageable pageable);
    Page<LocationEntity> findByDepartment_NameContainingIgnoreCase(String departmentName, Pageable pageable);


}