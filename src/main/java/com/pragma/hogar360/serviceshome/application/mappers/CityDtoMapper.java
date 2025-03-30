package com.pragma.hogar360.serviceshome.application.mappers;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CityResponse;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "saveCityRequest.name", target = "name")
    @Mapping(source = "saveCityRequest.description", target = "description")
    @Mapping(source = "saveCityRequest.departmentName", target = "department.name")
    CityModel requestToModel(SaveCityRequest saveCityRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CityResponse modelToResponse(CityModel cityModel);
}