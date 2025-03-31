package com.pragma.hogar360.serviceshome.domain.model;

public class HomeDetailsModel {
    private String description;
    private CategoryModel category;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;

    public HomeDetailsModel(String description, CategoryModel category, Integer numberOfRooms, Integer numberOfBathrooms) {
        this.description = description;
        this.category = category;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
    }
    public HomeDetailsModel(){}

    public String getDescription() {
        return description;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(Integer numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }
}