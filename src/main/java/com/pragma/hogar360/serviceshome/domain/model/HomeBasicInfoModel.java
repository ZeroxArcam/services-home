package com.pragma.hogar360.serviceshome.domain.model;

public class HomeBasicInfoModel {
    private String name;
    private Double price;
    private LocationModel location;

    public HomeBasicInfoModel(String name, Double price, LocationModel location) {
        this.name = name;
        this.price = price;
        this.location = location;
    }
    public HomeBasicInfoModel(){}

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
