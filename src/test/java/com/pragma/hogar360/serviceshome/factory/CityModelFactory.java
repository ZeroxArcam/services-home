package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;

public class CityModelFactory {

    public static DepartmentModel createDepartmentModel(long id, String name, String description) {
        return new DepartmentModel(id, name, description);
    }

    public static CityModel createCityModel(long id, String name, String description, DepartmentModel department) {
        return new CityModel(id, name, description, department);
    }

    public static CityModel createDefaultCityModel() {
        DepartmentModel defaultDepartment = createDepartmentModel(1L, "Default Department", "Default Department Description");
        return createCityModel(1L, "Default City", "This is a default city description.", defaultDepartment);
    }

    public static CityModel createCityModelWithName(String name, DepartmentModel department) {
        return createCityModel(1L, name, "Default description", department);
    }

    public static CityModel createCityModelWithDescription(String description, DepartmentModel department) {
        return createCityModel(1L, "Default name", description, department);
    }

    public static CityModel createCityModelWithId(long id, DepartmentModel department) {
        return createCityModel(id, "Default name", "Default description", department);
    }

    public static CityModel createCityModelWithDepartment(DepartmentModel department) {
        return createCityModel(1L, "Default name", "Default description", department);
    }

    public static CityModel createCityModelWithNullDepartment(String name, String description) {
        return new CityModel(1L, name, description, null);
    }
}