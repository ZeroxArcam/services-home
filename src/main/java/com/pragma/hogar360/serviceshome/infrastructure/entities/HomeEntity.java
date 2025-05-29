package com.pragma.hogar360.serviceshome.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "homes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @Column(nullable = false, length = 120)
    private String address;

    @Column(nullable = false)
    private Integer numberOfRooms;

    @Column(nullable = false)
    private Integer numberOfBathrooms;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate activePublicationDate;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PublicationStatus publicationStatus;

    public enum PublicationStatus {
        PUBLISHED,
        PUBLICATION_PAUSED,
        TRANSACTION_IN_PROGRESS,
        TRANSACTION_COMPLETED
    }


    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;



}