package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.HomeBasicInfoModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;

public class HomeBasicInfoModelFactory {

    public static HomeBasicInfoModel createHomeBasicInfoModel(String name, String address, Double price, LocationModel location) {
        return new HomeBasicInfoModel(name, address, price, location);
    }

    public static HomeBasicInfoModel createDefaultHomeBasicInfoModel() {
        return createHomeBasicInfoModel("Default Name", "Default Address", 1000.0, LocationModelFactory.createDefaultLocationModel());
    }

    public static HomeBasicInfoModel createHomeBasicInfoModelWithName(String name) {
        return createHomeBasicInfoModel(name, "Default Address", 1000.0, LocationModelFactory.createDefaultLocationModel());
    }

    public static HomeBasicInfoModel createHomeBasicInfoModelWithAddress(String address) {
        return createHomeBasicInfoModel("Default Name", address, 1000.0, LocationModelFactory.createDefaultLocationModel());
    }

    public static HomeBasicInfoModel createHomeBasicInfoModelWithPrice(Double price) {
        return createHomeBasicInfoModel("Default Name", "Default Address", price, LocationModelFactory.createDefaultLocationModel());
    }

    public static HomeBasicInfoModel createHomeBasicInfoModelWithLocation(LocationModel location) {
        return createHomeBasicInfoModel("Default Name", "Default Address", 1000.0, location);
    }
}