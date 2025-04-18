package com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql;

import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, Long>{
    boolean existsByAddress(String address);
    List<HomeEntity> findByActivePublicationDateAndPublicationStatus(LocalDate publicationDate, HomeEntity.PublicationStatus publicationStatus);

    @Query("""
            SELECT h FROM HomeEntity h
            WHERE h.activePublicationDate >= :currentDate
            AND h.publicationStatus = 'PUBLISHED'
            AND (:locationId IS NULL OR h.location.id = :locationId)
            AND (:categoryId IS NULL OR h.category.id = :categoryId)
            AND (:minRooms IS NULL OR h.numberOfRooms >= :minRooms)
            AND (:maxRooms IS NULL OR h.numberOfRooms <= :maxRooms)
            AND (:minBathrooms IS NULL OR h.numberOfBathrooms >= :minBathrooms)
            AND (:maxBathrooms IS NULL OR h.numberOfBathrooms <= :maxBathrooms)
            AND (:minPrice IS NULL OR h.price >= :minPrice)
            AND (:maxPrice IS NULL OR h.price <= :maxPrice)
            AND (:userId IS NULL OR h.userId = :userId)
            AND (:homeId IS NULL OR h.id = :homeId)""")
    Page<HomeEntity> findFilteredHomes(
            @Param("currentDate") LocalDate currentDate,
            @Param("locationId") Long locationId,
            @Param("categoryId") Long categoryId,
            @Param("minRooms") Integer minRooms,
            @Param("maxRooms") Integer maxRooms,
            @Param("minBathrooms") Integer minBathrooms,
            @Param("maxBathrooms") Integer maxBathrooms,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("userId") Long userId,
            @Param("homeId") Long homeId,
            Pageable pageable);
}