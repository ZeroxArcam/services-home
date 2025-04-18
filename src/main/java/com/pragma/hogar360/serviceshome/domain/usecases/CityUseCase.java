package com.pragma.hogar360.serviceshome.domain.usecases;
import com.pragma.hogar360.serviceshome.domain.exceptions.DepartmentNotFoundException;
import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CityServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.DomainConstants;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

public class CityUseCase implements CityServicePort {

    private final CityPersistencePort cityPersistencePort;
    private final DepartmentPersistencePort departmentPersistencePort;

    public CityUseCase(CityPersistencePort cityPersistencePort, DepartmentPersistencePort departmentPersistencePort) {
        this.cityPersistencePort = cityPersistencePort;
        this.departmentPersistencePort = departmentPersistencePort;
    }

    @Override
    public void save(CityModel city) {
        Validation.validateName(city.getName());
        Validation.validationCityDepartmentDescription(city.getDescription());

        DepartmentModel department = existDepartment(city.getDepartment());
        city.setDepartment(department);
        if (cityPersistencePort.existsByNameAndDepartmentName(city.getName(),department.getName())) {
            throw new DuplicateDepartmentNameException(DomainConstants.ALREADY_EXISTS);
        }
        cityPersistencePort.save(city);
    }

    private DepartmentModel existDepartment(DepartmentModel department) {
        if (department == null || department.getName() == null) {
            throw new DepartmentNotFoundException(DomainConstants.NOT_FOUND);
        } else {
            return department;
        }
    }
}