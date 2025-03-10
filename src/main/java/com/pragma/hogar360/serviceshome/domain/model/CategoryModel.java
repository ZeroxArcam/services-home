package com.pragma.hogar360.serviceshome.domain.model;

import com.pragma.hogar360.serviceshome.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyDescriptionException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;
import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;

import java.util.Objects;

/**
 * Represents a category within the services home domain.
 * This class encapsulates the data and validation logic for a category.
 */

public class CategoryModel {
    private Long id;
    private String name;
    private String description;

    /**
     * Default constructor for CategoryModel. Required by JPA.
     */
    public CategoryModel() {
    }

    /**
     * Constructs a new CategoryModel with the specified details.
     *
     * @param id          The unique identifier of the category.
     * @param name        The name of the category.
     * @param description The description of the category.
     * @throws NameMaxSizeExceededException     If the name exceeds the maximum allowed length.
     * @throws DescriptionMaxSizeExceededException If the description exceeds the maximum allowed length.
     * @throws IllegalArgumentException         If the name or description is blank.
     * @throws NullPointerException             If the name or description is null.
     */
    public CategoryModel(Long id, String name, String description) {
        this.id = id;
        this.name = Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        validateName(name);
        validateDescription(description);
    }

    /**
     * Validates the name of the category.
     *
     * @param name The name to validate.
     * @throws NameMaxSizeExceededException If the name exceeds the maximum allowed length.
     * @throws IllegalArgumentException     If the name is blank.
     */
    private void validateName(String name) {
        if (name.length() > 50) {
            throw new NameMaxSizeExceededException(DomainConstants.FIELD_NAME_MAX_LENGTH_MESSAGE);
        }
        if (name.isBlank()) {
            throw new EmptyNameException(DomainConstants.FIELD_NAME_EMPTY_MESSAGE);
        }
    }

    /**
     * Validates the description of the category.
     *
     * @param description The description to validate.
     * @throws DescriptionMaxSizeExceededException If the description exceeds the maximum allowed length.
     * @throws IllegalArgumentException             If the description is blank.
     */
    private void validateDescription(String description) {
        if (description.length() > 90) {
            throw new DescriptionMaxSizeExceededException(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE);
        }
        if (description.isBlank()) {
            throw new EmptyDescriptionException(DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE);
        }
    }

    /**
     * Gets the unique identifier of the category.
     *
     * @return The category ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the category.
     *
     * @return The category name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the category.
     *
     * @return The category description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the unique identifier of the category.
     *
     * @param id The new category ID.
     */
    public void setId(Long id) {this.id = id;}


    /**
     * Sets the name of the category, validating it first.
     *
     * @param name The new category name.
     * @throws NameMaxSizeExceededException If the name exceeds the maximum allowed length.
     * @throws IllegalArgumentException     If the name is blank.
     * @throws NullPointerException         If the name is null.
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        validateName(name);
    }

    /**
     * Sets the description of the category, validating it first.
     *
     * @param description The new category description.
     * @throws DescriptionMaxSizeExceededException If the description exceeds the maximum allowed length.
     * @throws IllegalArgumentException             If the description is blank.
     * @throws NullPointerException                 If the description is null.
     */
    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        validateDescription(description);
    }


}
