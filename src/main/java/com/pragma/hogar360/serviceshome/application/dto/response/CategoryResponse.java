package com.pragma.hogar360.serviceshome.application.dto.response;

/**
 * Record representing the response containing category information.
 *
 * @param id          The unique identifier of the category.
 * @param name        The name of the category.
 * @param description The description of the category.
 */
public record CategoryResponse(Long id, String name, String description) {
}