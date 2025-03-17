package com.pragma.hogar360.serviceshome.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents the Department entity for database persistence.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {

    /**
     * The unique identifier for the department.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the department.
     * It is required, has a maximum length of 50 characters, and must be unique.
     */
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    /**
     * The description of the department.
     * It is required and has a maximum length of 120 characters.
     */
    @Column(nullable = false, length = 120)
    private String description;

}
