package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, Long> {
    boolean existsByAddress(String address);
    List<HomeEntity> findByActivePublicationDateAndPublicationStatus(LocalDate publicationDate, HomeEntity.PublicationStatus publicationStatus);
}