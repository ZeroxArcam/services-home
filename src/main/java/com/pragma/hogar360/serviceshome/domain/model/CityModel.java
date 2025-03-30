package com.pragma.hogar360.serviceshome.domain.model;
public class CityModel {

    private Long id;
    private String name;
    private String description;
    private DepartmentModel department;

    public CityModel(long id, String name, String description, DepartmentModel department) {
        this.name = name;
        this.description = description;
        this.department = department;
        this.id = id;
    }
    public CityModel() {}

    public DepartmentModel getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }

}