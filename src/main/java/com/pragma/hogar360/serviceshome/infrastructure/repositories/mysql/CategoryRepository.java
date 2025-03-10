package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.infrastructure.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Category entities in the database.
 * Provides CRUD operations and custom query methods for CategoryEntity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /**
     * Finds a CategoryEntity by its name.
     *
     * @param name The name of the category to find.
     * @return An Optional containing the CategoryEntity if found, or an empty Optional if not found.
     */
    Optional<CategoryEntity> findByName(String name);

    /**
     * Retrieves a paginated list of all Category entities.
     *
     * @param pageable The pagination information.
     * @return A Page containing the Category entities.
     */
    Page<CategoryEntity> findAll(Pageable pageable);
}