package com.pragma.hogar360.serviceshome.domain.model;

public class HomeBasicInfoModel {
    private String name;
    private String address;
    private Double price;
    private LocationModel location;

    public HomeBasicInfoModel(String name,String address, Double price, LocationModel location) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.location = location;
    }
    public HomeBasicInfoModel(){}

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}