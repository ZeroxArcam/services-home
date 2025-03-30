package com.pragma.hogar360.serviceshome.application.mappers;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveDepartmentRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.DepartmentResponse;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "saveDepartmentRequest.name", target = "name")
    @Mapping(source = "saveDepartmentRequest.description", target = "description")
    DepartmentModel requestToModel(SaveDepartmentRequest saveDepartmentRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    DepartmentResponse modelToResponse(DepartmentModel departmentModel);
}