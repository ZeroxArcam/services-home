package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
/**
 * Port interface defining the contract for city persistence operations.
 * This interface provides methods for saving, checking existence, and finding city domain models.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
public interface CityPersistencePort {
    void save(CityModel city);
    boolean existsById(Long id);
    boolean existsByNameAndDepartmentName(String name, String department);

}