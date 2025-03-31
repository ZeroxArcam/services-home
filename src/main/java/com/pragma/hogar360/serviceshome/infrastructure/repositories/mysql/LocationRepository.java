package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;
import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long>, JpaSpecificationExecutor<LocationEntity> {

    boolean existsByCityId(Long cityId);
    Optional<LocationEntity> findById(Long id);
    boolean existsByNeighborhood(String neighborhood);
    @Query("SELECT l FROM LocationEntity l WHERE LOWER(l.city.name) LIKE LOWER(CONCAT('%', :text, '%')) OR LOWER(l.city.department.name) LIKE LOWER(CONCAT('%', :text, '%'))")
    Page<LocationEntity> findByCityNameOrDepartmentNameContainingIgnoreCase(@Param("text") String text, Pageable pageable);
}