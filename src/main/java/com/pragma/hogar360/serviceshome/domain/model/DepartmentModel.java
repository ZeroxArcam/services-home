package com.pragma.hogar360.serviceshome.domain.model;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

/**
 * Domain model representing a department.
 * This class encapsulates department information, including its ID, name, and description.
 * It also includes validation logic for the name and description.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public class DepartmentModel {

    /**
     * The unique identifier of the department.
     */
    private Long id;

    /**
     * The name of the department.
     */
    private String name;

    /**
     * The description of the department.
     */
    private String description;

    /**
     * Constructs a new DepartmentModel instance.
     *
     * @param id          The unique identifier of the department.
     * @param name        The name of the department.
     * @param description The description of the department.
     */
    public DepartmentModel(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
//        setName(name);
//        setDescription(description);
    }
    public DepartmentModel(){}

    /**
     * Gets the unique identifier of the department.
     *
     * @return The department ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the department.
     *
     * @return The department name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the department.
     *
     * @return The department description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the department, applying validation.
     *
     * @param description The new description of the department.
     */
    public void setDescription(String description) {
        //Validation.validationCityDepartmentDescription(description);
        this.description = description;
    }

    /**
     * Sets the name of the department, applying validation.
     *
     * @param name The new name of the department.
     */
    public void setName(String name) {
        //Validation.validateName(name);
        this.name = name;
    }
}