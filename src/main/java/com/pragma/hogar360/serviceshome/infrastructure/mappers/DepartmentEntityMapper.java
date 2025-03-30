package com.pragma.hogar360.serviceshome.infrastructure.mappers;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentEntityMapper {

    @Mapping(target = "id", ignore = true)
    DepartmentEntity toEntity(DepartmentModel departmentModel);

    @Mapping(target = "id", ignore = true)
    DepartmentModel toModel(DepartmentEntity departmentEntity);
}