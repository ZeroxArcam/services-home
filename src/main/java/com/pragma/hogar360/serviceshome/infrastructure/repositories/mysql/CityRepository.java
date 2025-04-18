package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndDepartmentName(String name, String department);
}