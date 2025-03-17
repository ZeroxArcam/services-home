package com.pragma.hogar360.serviceshome.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a location in the database.
 * This class is mapped to the "locations" table and contains information about a location,
 * including its ID, associated city, and associated department.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Entity
@Table(name = "locations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"city_id", "department_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {

    /**
     * The unique identifier of the location.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The city associated with the location.
     * It is a many-to-one relationship with the CityEntity.
     */
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    /**
     * The department associated with the location.
     * It is a many-to-one relationship with the DepartmentEntity.
     */
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;
}