package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;

public class LocationModelFactory {

    public static LocationModel createLocationModel(Long id, CityModel city, DepartmentModel department) {
        return new LocationModel(id, city, department);
    }

    public static LocationModel createDefaultLocationModel() {
        CityModel city = CityModelFactory.createDefaultCityModel();
        DepartmentModel department = DepartmentModelFactory.createDefaultDepartmentModel();
        return createLocationModel(1L, city, department);
    }

    public static LocationModel createLocationModelWithCity(CityModel city) {
        DepartmentModel department = DepartmentModelFactory.createDefaultDepartmentModel();
        return createLocationModel(1L, city, department);
    }

    public static LocationModel createLocationModelWithDepartment(DepartmentModel department) {
        CityModel city = CityModelFactory.createDefaultCityModel();
        return createLocationModel(1L, city, department);
    }

    public static LocationModel createLocationModelWithId(Long id) {
        CityModel city = CityModelFactory.createDefaultCityModel();
        DepartmentModel department = DepartmentModelFactory.createDefaultDepartmentModel();
        return createLocationModel(id, city, department);
    }

}