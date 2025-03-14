package com.pragma.hogar360.serviceshome.infrastructure.exceptionshandler;

import com.pragma.hogar360.serviceshome.domain.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for REST controllers.
 */
@ControllerAdvice
public class ControllerAdvisor {

    /**
     * Handles NameMaxSizeExceededException.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity with the error response.
     */
    @ExceptionHandler(NameMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleNameMaxSizeExceededException(NameMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.NAME_MAX_SIZE_MESSAGE,
                LocalDateTime.now()));
    }

    /**
     * Handles DescriptionMaxSizeExceededException.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity with the error response.
     */
    @ExceptionHandler(DescriptionMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionMaxSizeExceededException(DescriptionMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.DESCRIPTION_MAX_SIZE_MESSAGE,
                LocalDateTime.now()));
    }
    /**
     * Handles EmptyNameException.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity with the error response.
     */
    @ExceptionHandler(EmptyNameException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyNameException(EmptyNameException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.NAME_EMPTY,
                LocalDateTime.now()));
    }

    /**
     * Handles EmptyDescriptionException.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity with the error response.
     */
    @ExceptionHandler(EmptyDescriptionException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyDescriptionException(EmptyDescriptionException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.DESCRIPTION_EMPTY,
                LocalDateTime.now()));
    }

    /**
     * Handles CategoryAlreadyExistsException.
     *
     * @param exception The exception to handle.
     * @return A ResponseEntity with the error response.
     */
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.CATEGORY_EXISTS_EXCEPTION,
                LocalDateTime.now()));
    }
}