package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, Long> {
    boolean existsById(Long id);
}
