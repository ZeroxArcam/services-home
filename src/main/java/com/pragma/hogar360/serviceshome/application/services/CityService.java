package com.pragma.hogar360.serviceshome.application.services;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCityResponse;

public interface CityService {
    SaveCityResponse createCity(SaveCityRequest request);
}