package com.pragma.hogar360.serviceshome.domain.model;
public class LocationModel {

    private Long id;
    private CityModel city;
    private String neighborhood;

    public LocationModel(Long id, CityModel city, String neighborhood) {
        this.id = id;
        this.city = city;
        this.neighborhood = neighborhood;
    }
    public LocationModel(){}
    public Long getId() {
        return id;
    }
    public CityModel getCity() {
        return city;
    }
    public String getCityName() {
        return city.getName();

    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public void setCity(CityModel city) {
        this.city = city;
    }
    public void setId(Long id) {
        this.id = id;
    }


}