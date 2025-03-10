package com.pragma.hogar360.serviceshome.application.dto.response;

import java.time.LocalDateTime;

/**
 * Record representing the response after saving a category.
 *
 * @param message The message indicating the result of the save operation.
 * @param time    The timestamp when the operation was completed.
 */
public record SaveCategoryResponse(String message, LocalDateTime time) {
}