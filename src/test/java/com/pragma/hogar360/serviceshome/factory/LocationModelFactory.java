package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;

public class LocationModelFactory {

    public static LocationModel createLocationModel(Long id, CityModel city, String neighborhood) {
        return new LocationModel(id, city, neighborhood);
    }

    public static LocationModel createDefaultLocationModel() {
        CityModel city = CityModelFactory.createDefaultCityModel();
        return createLocationModel(1L, city, "Default Neighborhood");
    }

    public static LocationModel createLocationModelWithCity(CityModel city) {
        return createLocationModel(1L, city, "Default Neighborhood");
    }

    public static LocationModel createLocationModelWithNeighborhood(String neighborhood) {
        CityModel city = CityModelFactory.createDefaultCityModel();
        return createLocationModel(1L, city, neighborhood);
    }

    public static LocationModel createLocationModelWithId(Long id) {
        CityModel city = CityModelFactory.createDefaultCityModel();
        return createLocationModel(id, city, "Default Neighborhood");
    }
}