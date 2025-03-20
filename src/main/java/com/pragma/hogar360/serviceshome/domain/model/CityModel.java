package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

/**
 * Domain model representing a city.
 * This class encapsulates city information, including its ID, name, and description.
 * It also includes validation logic for the name and description.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public class CityModel {

    /**
     * The unique identifier of the city.
     */
    private final Long id;

    /**
     * The name of the city.
     */
    private String name;

    /**
     * The description of the city.
     */
    private String description;

    /**
     * Constructs a new CityModel instance.
     *
     * @param id          The unique identifier of the city.
     * @param name        The name of the city.
     * @param description The description of the city.
     */
    public CityModel(long id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Gets the unique identifier of the city.
     *
     * @return The city ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the city.
     *
     * @return The city name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the city.
     *
     * @return The city description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the city, applying validation.
     *
     * @param description The new description of the city.
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Sets the name of the city, applying validation.
     *
     * @param name The new name of the city.
     */
    public void setName(String name) {


        this.name = name;
    }

}