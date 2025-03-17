package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CityResponse;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between CityEntity and CityModel.
 * Uses MapStruct for automatic code generation.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityDtoMapper {

    /**
     * Converts a SaveCityRequest and DepartmentModel to a CityModel.
     *
     * @param saveCityRequest The SaveCityRequest to convert.
     * @return The corresponding CityModel.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "saveCityRequest.name", target = "name")
    @Mapping(source = "saveCityRequest.description", target = "description")
    CityModel requestToModel(SaveCityRequest saveCityRequest);

    /**
     * Converts a CityModel to a CityResponse.
     *
     * @param cityModel The CityModel to convert.
     * @return The corresponding CityResponse.
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CityResponse modelToResponse(CityModel cityModel);
}