package com.pragma.hogar360.serviceshome.infrastructure.exceptionshandler;

/**
 * Constants for exception messages.
 */
public final class ExceptionConstants {

    /**
     * Private constructor to prevent instantiation.
     */
    private ExceptionConstants(){}

    /**
     * Message for when the category name exceeds the maximum size.
     */
    public static final String NAME_MAX_SIZE_MESSAGE = "The name of the category can not exceed 50 characters";

    /**
     * Message for when the category description exceeds the maximum size.
     */
    public static final String DESCRIPTION_MAX_SIZE_MESSAGE = "The description of the category can not exceed 90 characters";

    /**
     * Message for when the category already exists.
     */
    public static final String CATEGORY_EXISTS_EXCEPTION = "The category already exists";

    /**
     * Message for when the category was not exists.
     */
    public static final String CATEGORY_NOT_FOUND_EXCEPTION = "The category was not found";

    public static final String DESCRIPTION_EMPTY = "The description of the category can not be empty";

    public static final String NAME_EMPTY = "The name of the category can not be empty";
}