package com.pragma.hogar360.serviceshome.domain.utils.constants;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;

import java.util.Objects;
import java.text.Normalizer;

public class Validation {

    private Validation(){}
    /**

     * Validates a name string.

     *

     * @param name The name string to validate.

     * @throws NullPointerException If the name is null.

     * @throws NameMaxSizeExceededException If the name exceeds the maximum allowed length.

     * @throws EmptyNameException If the name is blank.

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
     //     * Validates a description string.
     //     *
     //     * @param description The description string to validate.
     //     * @throws NullPointerException              If the description is null.
     //     * @throws DescriptionMaxSizeExceededException If the description exceeds the maximum allowed length.
     //     * @throws EmptyDescriptionException           If the description is blank.
     //     */
    public static void validateDescription(String description) {
        Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        if (description.length() > DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionMaxSizeExceededException(DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE);
        }
        if (description.isBlank()) {
            throw new EmptyDescriptionException(DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE);
        }
    }

    public static void validationCityDepartmentDescription(String description) {
        Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        if (description.length() > DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH) {
            throw new CityDepartmentDescriptionMaxSizeExceededException(DomainConstants.FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH_MESSAGE);
        }
        if (description.isBlank()) {
            throw new EmptyDescriptionException(DomainConstants.FIELD_DESCRIPTION_EMPTY_MESSAGE);
        }
    }


    public static String normalizeText(String text) {
        if (text == null) {
            return null;
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    public static void ValidatePageAndSize(Integer page, Integer size) {
        if (page < DomainConstants.NUMBER_PAGE_INT) {
            throw new NumberPageException(DomainConstants.NUMBER_PAGE);
        }
        if (size < DomainConstants.SIZE_PAGE_INT) {
            throw new SizePageException(DomainConstants.SIZE_PAGE);
        }
    }



}
