package com.pragma.hogar360.serviceshome.domain.ports.in;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;

public interface CityServicePort {

    void save(CityModel city);
}