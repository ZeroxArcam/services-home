package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;

public class CityModelFactory {

    public static CityModel createCityModel(long id, String name, String description) {
        return new CityModel(id, name, description);
    }

    public static CityModel createDefaultCityModel() {
        return createCityModel(1L, "Default City", "This is a default city description.");
    }

    public static CityModel createCityModelWithName(String name) {
        return createCityModel(1L, name, "Default description");
    }

    public static CityModel createCityModelWithDescription(String description) {
        return createCityModel(1L, "Default name", description);
    }

    public static CityModel createCityModelWithId(long id) {
        return createCityModel(id, "Default name", "Default description");
    }


}