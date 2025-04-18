package com.pragma.hogar360.serviceshome.domain.model;

public class DepartmentModel {

    private Long id;
    private String name;
    private String description;

    public DepartmentModel(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public DepartmentModel(){}
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
}