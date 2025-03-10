package com.pragma.hogar360.serviceshome.application.dto.request;

/**
 * Record representing the request to save a new category.
 *
 * @param name        The name of the category.
 * @param description The description of the category.
 */
public record SaveCategoryRequest(String name, String description) {
}