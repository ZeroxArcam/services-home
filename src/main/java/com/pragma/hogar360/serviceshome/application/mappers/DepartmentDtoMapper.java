package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveDepartmentRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.DepartmentResponse;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface for converting between department DTOs and domain models.
 * Utilizes MapStruct for automatic mapping code generation.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentDtoMapper {

    /**
     * Converts a {@link SaveDepartmentRequest} object to a {@link DepartmentModel} object.
     * The 'id' field is ignored during the conversion.
     *
     * @param saveDepartmentRequest The request DTO to convert.
     * @return The resulting domain model.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "saveDepartmentRequest.name", target = "name")
    @Mapping(source = "saveDepartmentRequest.description", target = "description")
    DepartmentModel requestToModel(SaveDepartmentRequest saveDepartmentRequest);

    /**
     * Converts a {@link DepartmentModel} object to a {@link DepartmentResponse} object.
     *
     * @param departmentModel The domain model to convert.
     * @return The resulting response DTO.
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    DepartmentResponse modelToResponse(DepartmentModel departmentModel);
}