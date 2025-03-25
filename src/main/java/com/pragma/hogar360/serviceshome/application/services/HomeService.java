package com.pragma.hogar360.serviceshome.application.services;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveHomeResponse;

public interface HomeService {
    SaveHomeResponse createHome(SaveHomeRequest request);
}
