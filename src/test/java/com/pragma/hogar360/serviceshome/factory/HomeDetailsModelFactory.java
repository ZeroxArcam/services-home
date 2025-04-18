package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeDetailsModel;

public class HomeDetailsModelFactory {

    public static HomeDetailsModel createHomeDetailsModel(String description, CategoryModel category, Integer numberOfRooms, Integer numberOfBathrooms) {
        return new HomeDetailsModel(description, category, numberOfRooms, numberOfBathrooms);
    }

    public static HomeDetailsModel createDefaultHomeDetailsModel() {
        return createHomeDetailsModel("Default Description", CategoryModelFactory.createDefaultCategoryModel(), 3, 2);
    }

    public static HomeDetailsModel createHomeDetailsModelWithDescription(String description) {
        return createHomeDetailsModel(description, CategoryModelFactory.createDefaultCategoryModel(), 3, 2);
    }

    public static HomeDetailsModel createHomeDetailsModelWithCategory(CategoryModel category) {
        return createHomeDetailsModel("Default Description", category, 3, 2);
    }

    public static HomeDetailsModel createHomeDetailsModelWithRooms(Integer numberOfRooms) {
        return createHomeDetailsModel("Default Description", CategoryModelFactory.createDefaultCategoryModel(), numberOfRooms, 2);
    }

    public static HomeDetailsModel createHomeDetailsModelWithBathrooms(Integer numberOfBathrooms) {
        return createHomeDetailsModel("Default Description", CategoryModelFactory.createDefaultCategoryModel(), 3, numberOfBathrooms);
    }
}