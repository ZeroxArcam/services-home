package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for City entity operations using JPA.
 * Extends {@link JpaRepository} to provide standard database operations.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    /**
     * Finds a CityEntity by its name.
     *
     * @param name The name of the city to find.
     * @return An {@link Optional} containing the CityEntity, or an empty {@link Optional} if not found.
     */
    Optional<CityEntity> findByName(String name);

    /**
     * Checks if a CityEntity with the given name exists.
     *
     * @param name The name of the city to check.
     * @return {@code true} if a CityEntity with the name exists, {@code false} otherwise.
     */
    boolean existsByName(String name);
}