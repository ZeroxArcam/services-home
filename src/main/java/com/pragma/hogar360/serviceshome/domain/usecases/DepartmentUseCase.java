package com.pragma.hogar360.serviceshome.domain.usecases;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.DepartmentServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;
import java.util.Optional;

public class DepartmentUseCase implements DepartmentServicePort {

    private final DepartmentPersistencePort departmentPersistencePort;

    public DepartmentUseCase(DepartmentPersistencePort departmentPersistencePort) {
        this.departmentPersistencePort = departmentPersistencePort;
    }

    @Override
    public void save(DepartmentModel department) {
        Validation.validateName(department.getName());
        Validation.validationCityDepartmentDescription(department.getDescription());

        if (departmentPersistencePort.existsByName(department.getName())) {
            throw new DuplicateDepartmentNameException(DomainConstants.ALREADY_EXISTS);
        }
        departmentPersistencePort.save(department);
    }
//
//    @Override
//    public Optional<DepartmentModel> getDepartmentByName(String name) {
//        return departmentPersistencePort.findByName(name);
//    }
}