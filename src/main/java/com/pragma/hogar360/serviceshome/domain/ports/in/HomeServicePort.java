package com.pragma.hogar360.serviceshome.domain.ports.in;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;

public interface HomeServicePort {
    void save(HomeModel home);
}