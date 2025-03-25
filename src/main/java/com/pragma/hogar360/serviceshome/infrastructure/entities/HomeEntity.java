package com.pragma.hogar360.serviceshome.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing a home in the database.
 * This class is mapped to the "homes" table and contains information about a home,
 * including its ID, name, description, category, number of rooms, number of bathrooms,
 * price, location, active publication date, publication status, and publication date.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Entity
@Table(name = "homes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeEntity {

    /**
     * The unique identifier of the home.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the home.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * The description of the home.
     */
    @Column(nullable = false, length = 500)
    private String description;

    /**
     * The category associated with the home.
     * It is a many-to-one relationship with the CategoryEntity.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    /**
     * The number of rooms in the home.
     */
    @Column(nullable = false)
    private Integer numberOfRooms;

    /**
     * The number of bathrooms in the home.
     */
    @Column(nullable = false)
    private Integer numberOfBathrooms;

    /**
     * The price of the home.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * The location associated with the home.
     * It is a many-to-one relationship with the LocationEntity.
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    /**
     * The active publication date of the home.
     */
    @Column(nullable = false)
    private LocalDate activePublicationDate;

    /**
     * The publication status of the home.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PublicationStatus publicationStatus;

    public enum PublicationStatus {
        PUBLISHED,
        PUBLICATION_PAUSED,
        TRANSACTION_IN_PROGRESS,
        TRANSACTION_COMPLETED
    }
    /**
     * The publication date of the home.
     */
    @Column(nullable = false)
    private LocalDate publicationDate;

    /**
     * Enum representing the publication status of a home.
     */

}