package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.CityNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;

/**
 * Domain model representing a location.
 * This class encapsulates location information, including its ID, city, and department.
 * It also includes validation to ensure that city and department are not null.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public class LocationModel {

    /**
     * The unique identifier of the location.
     */
    private Long id;

    /**
     * The city associated with the location.
     */
    private CityModel city;

    /**
     * The department associated with the location.
     */
    private DepartmentModel department;

    /**
     * Constructs a new LocationModel instance.
     *
     * @param id         The unique identifier of the location.
     * @param city       The city associated with the location.
     * @param department The department associated with the location.
     * @throws CityNotFoundException    If the city is null.
     * @throws DepartmentNotFoundException If the department is null.
     */
    public LocationModel(Long id, CityModel city, DepartmentModel department) {

        this.id = id;
        this.city = city;
        this.department = department;
    }

    /**
     * Gets the unique identifier of the location.
     *
     * @return The location ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the city associated with the location.
     *
     * @return The city model.
     */
    public CityModel getCity() {
        return city;
    }

    /**
     * Gets the department associated with the location.
     *
     * @return The department model.
     */
    public DepartmentModel getDepartment() {
        return department;
    }

    /**
     * Gets the name of the city associated with the location.
     *
     * @return The city name, or null if the city is null.
     */
    public String getCityName() {
        return city.getName();

    }

    /**
     * Gets the name of the department associated with the location.
     *
     * @return The department name, or null if the department is null.
     */
    public String getDepartmentName() {
        return department.getName();
    }

}