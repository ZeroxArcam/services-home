package com.pragma.hogar360.serviceshome.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a city in the database.
 * This class is mapped to the "cities" table and contains information about a city, including its ID, name, and description.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {

    /**
     * The unique identifier of the city.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the city.
     * It is required, has a maximum length of 50 characters, and must be unique.
     */
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    /**
     * The description of the city.
     * It is required and has a maximum length of 120 characters.
     */
    @Column(nullable = false, length = 120)
    private String description;
}