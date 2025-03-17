package com.pragma.hogar360.serviceshome.infrastructure.mappers;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
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
public interface CityEntityMapper {

    /**
     * Converts a CityEntity to a CityModel.
     *
     * @param cityEntity The CityEntity to convert.
     * @return The corresponding CityModel.
     */
    @Mapping(target = "id", ignore = true)
    CityModel toModel(CityEntity cityEntity);

    /**
     * Converts a CityModel to a CityEntity.
     *
     * @param cityModel The CityModel to convert.
     * @return The corresponding CityEntity.
     */
    @Mapping(target = "id", ignore = true)
    CityEntity toEntity(CityModel cityModel);

}