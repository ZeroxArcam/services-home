package com.pragma.hogar360.serviceshome.domain.utils.constants;

public final class DomainConstants {

    private DomainConstants() {throw new IllegalStateException("Utility class");}

    public static final String FIELD_NAME_NULL_MESSAGE = "Category name cannot be null.";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Category description cannot be null.";
    public static final String FIELD_NAME_EMPTY_MESSAGE = "Category name cannot be empty.";
    public static final String FIELD_DESCRIPTION_EMPTY_MESSAGE = "Category description cannot be empty.";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Category name cannot exceed 50 characters.";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "Category description cannot exceed 90 characters.";
    public static final int FIELD_NAME_MAX_LENGTH = 50;
    public static final int FIELD_DESCRIPTION_MAX_LENGTH = 90;
    public static final String NUMBER_PAGE = "The number of pages can not be less than 0";
    public static final String SIZE_PAGE = "The size of pagas can not be less than 0";
}