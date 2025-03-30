package com.pragma.hogar360.serviceshome.domain.ports.out;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import java.util.Optional;

public interface DepartmentPersistencePort {

    void save(DepartmentModel department);
    boolean existsByName(String name);
    Optional<DepartmentModel> findByName(String name);
}