package com.pragma.hogar360.serviceshome.infrastructure.exceptionshandler;

public final class ExceptionConstants {

    private ExceptionConstants(){}

    public static final String NAME_MAX_SIZE_MESSAGE = "The name can not exceed 50 characters";
    public static final String DESCRIPTION_MAX_SIZE_MESSAGE = "The description of the category can not exceed 90 characters";
    public static final String CATEGORY_EXISTS_EXCEPTION = "The category already exists";
    public static final String CATEGORY_NOT_FOUND_EXCEPTION = "The category was not found";
    public static final String DESCRIPTION_EMPTY = "The description can not be empty";
    public static final String NAME_EMPTY = "The name can not be empty";
    public static final String NUMBER_PAGE = "The number of pages can not be less than 0";
    public static final String SIZE_PAGE = "The size of the page can not be less than 1";
    public static final String CITY_NAME_NOT_FOUND ="Name not found";
    public static final String DEPARTMENT_NOT_FOUND = "Department not found.";
    public static final String DUPLICATE_ENTITY_EXCEPTION = "Duplicated data.";
    public static final String FIELD_DESCRIPTION_CITY_DEPARTMENT_MAX_LENGTH_MESSAGE = "Description cannot exceed 120 characters.";
    public static final String DESCRIPTION_LOCATION_MAX_SIZE_MESSAGE = "The description can not exceed 120 characters";
    public static final String INVALID_PARAMETERS = "Invalid parameters.";
    public static final String INVALID_DATE = "Invalid date";
    public static final String INVALID_NUMBER_OF_ROOMS = "Invalid number of rooms";
    public static final String ADDRESS_NULL_OR_EMPTY = "Address cannot be null or empty.";
    public static final String ADDRESS_ALREADY_EXISTS = "Address already exists.";

}