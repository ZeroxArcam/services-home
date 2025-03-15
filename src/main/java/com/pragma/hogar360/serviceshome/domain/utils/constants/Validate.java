package com.pragma.hogar360.serviceshome.domain.utils.constants;

import com.pragma.hogar360.serviceshome.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyDescriptionException;
import com.pragma.hogar360.serviceshome.domain.exceptions.EmptyNameException;
import com.pragma.hogar360.serviceshome.domain.exceptions.NameMaxSizeExceededException;

import java.util.Objects;

/**
 * Utility class for validating properties.
 * Provides static methods for validating name and description strings.
 */
public class Validate {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Validate() {
    }

    /**
     * Validates a name string.
     *
     * @param name The name string to validate.
     * @throws NullPointerException         If the name is null.
     * @throws NameMaxSizeExceededException If the name exceeds the maximum allowed length.
     * @throws EmptyNameException           If the name is blank.
     */
    public static void validateName(String name) {
        Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        if (name.length() > DomainConstants.FIELD_NAME_MAX_LENGTH) {
            throw new NameMaxSizeExceededException(DomainConstants.FIELD_NAME_MAX_LENGTH_MESSAGE);
        }
        if (name.isBlank()) {
            throw new EmptyNameException(DomainConstants.FIELD_NAME_EMPTY_MESSAGE);
        }
    }

    /**
     * Validates a description string.
     *
     * @param description The description string to validate.
     * @throws NullPointerException              If the description is null.
     * @throws DescriptionMaxSizeExceededException If the description exceeds the maximum allowed length.
     * @throws EmptyDescriptionException           If the description is blank.
     */
    public static void validateDescription(String description) {
        Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        if (description.length() > DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionMaxSizeExceededException(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE);
        }
        if (description.isBlank()) {
            throw new EmptyDescriptionException(DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE);
        }
    }
}