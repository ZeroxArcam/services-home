package com.pragma.hogar360.serviceshome.domain.utils.constants;

public final class DomainConstants {

    private DomainConstants() {throw new IllegalStateException("Utility class");}

    public static final String FIELD_NAME_NULL_MESSAGE = "Name cannot be null.";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Description cannot be null.";
    public static final String FIELD_NAME_EMPTY_MESSAGE = "Name cannot be empty.";
    public static final String FIELD_DESCRIPTION_EMPTY_MESSAGE = "Description cannot be empty.";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Name cannot exceed 50 characters.";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "Description cannot exceed 90 characters.";
    public static final String FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH_MESSAGE = "Description cannot exceed 120 characters.";
    public static final int FIELD_NAME_MAX_LENGTH = 50;
    public static final int FIELD_DESCRIPTION_MAX_LENGTH = 90;
    public static final int FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH=120;
    public static final int SIZE_PAGE_INT = 1;
    public static final int NUMBER_PAGE_INT = 0;
    public static final int MAX_ALLOWED_DATE = 30;
    public static final int NUMBER_OF_ROOM_INT = 1;
    public static final String NUMBER_PAGE = "The number of pages can not be less than 0";
    public static final String SIZE_PAGE = "The size of pagas can not be less than 1";
    public static final String NOT_FOUND = "No such data exists.";
    public static final String ALREADY_EXISTS = "Data already exists.";
    public static final String INVALID_PARAMETERS = "Invalid parameters.";
    public static final String PUBLICATION_DATE_PAST_ERROR = "The publication date cannot be in the past and cannot exceed 30 days from the current date.";
    public static final String ACTIVE_DATE_BEFORE_PUBLICATION_ERROR = "The active publication date cannot be before the publication date and cannot exceed 30 days from the current date.";
    public static final String ID_LOCATION_HOME_INVALID ="Location ID can not be empty or null.";
    public static final String ADDRESS_ALREADY_EXISTS = "Address already exists.";
    public static final String ADDRESS_NULL_OR_EMPTY = "Address cannot be null or empty.";
    public static final String INVALID_NUMBER_OF_ROOMS = "Invalid number of rooms";

}