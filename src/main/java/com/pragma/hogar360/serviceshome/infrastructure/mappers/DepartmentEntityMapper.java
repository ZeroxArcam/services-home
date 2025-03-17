package com.pragma.hogar360.serviceshome.infrastructure.mappers;

import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between DepartmentEntity and DepartmentModel.
 * Uses MapStruct for automatic code generation.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentEntityMapper {

    /**
     * Converts a DepartmentModel to a DepartmentEntity.
     *
     * @param departmentModel The DepartmentModel to convert.
     * @return The corresponding DepartmentEntity.
     */
    DepartmentEntity toEntity(DepartmentModel departmentModel);

    /**
     * Converts a DepartmentEntity to a DepartmentModel.
     *
     * @param departmentEntity The DepartmentEntity to convert.
     * @return The corresponding DepartmentModel.
     */
    DepartmentModel toModel(DepartmentEntity departmentEntity);
}