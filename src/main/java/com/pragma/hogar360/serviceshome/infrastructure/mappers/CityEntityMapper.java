package com.pragma.hogar360.serviceshome.infrastructure.mappers;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityEntityMapper {

    //@Mapping(target = "id", ignore = true)
    @Mapping(source = "department", target = "department")
    CityModel toModel(CityEntity cityEntity);

    //@Mapping(target = "id", ignore = true)
    CityEntity toEntity(CityModel cityModel);

}