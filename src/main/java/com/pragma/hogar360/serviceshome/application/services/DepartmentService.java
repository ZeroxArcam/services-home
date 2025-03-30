package com.pragma.hogar360.serviceshome.application.services;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveDepartmentRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveDepartmentResponse;

public interface DepartmentService {
    SaveDepartmentResponse createDepartment(SaveDepartmentRequest request);
}