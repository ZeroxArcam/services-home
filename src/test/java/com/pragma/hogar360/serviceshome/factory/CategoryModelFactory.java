package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;

public class CategoryModelFactory {

    public static CategoryModel createCategoryModel(Long id, String name, String description) {
        return new CategoryModel(id, name, description);
    }

    public static CategoryModel createDefaultCategoryModel() {
        return createCategoryModel(1L, "Default Category", "This is a default category description.");
    }

    public static CategoryModel createCategoryModelWithName(String name) {
        return createCategoryModel(1L, name, "Default description");
    }

    public static CategoryModel createCategoryModelWithDescription(String description) {
        return createCategoryModel(1L, "Default name", description);
    }

    public static CategoryModel createCategoryModelWithId(Long id) {
        return createCategoryModel(id, "Default name", "Default description");
    }

}