package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;

public class DepartmentModelFactory {

    public static DepartmentModel createDepartmentModel(Long id, String name, String description) {
        return new DepartmentModel(id, name, description);
    }

    public static DepartmentModel createDefaultDepartmentModel() {
        return createDepartmentModel(1L, "Default Department", "This is a default department description.");
    }

    public static DepartmentModel createDepartmentModelWithName(String name) {
        return createDepartmentModel(1L, name, "Default description");
    }

    public static DepartmentModel createDepartmentModelWithDescription(String description) {
        return createDepartmentModel(1L, "Default name", description);
    }

    public static DepartmentModel createDepartmentModelWithId(Long id) {
        return createDepartmentModel(id, "Default name", "Default description");
    }

}