package com.pragma.hogar360.serviceshome.domain.ports.in;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import java.util.Optional;

public interface DepartmentServicePort {

    void save(DepartmentModel department);
    //Optional<DepartmentModel> getDepartmentByName(String name);
}