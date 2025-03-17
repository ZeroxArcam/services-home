package com.pragma.hogar360.serviceshome.infrastructure.mappers;

import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between Location domain models and entities.
 * This interface uses MapStruct to generate mapping code at compile time.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationEntityMapper {

    /**
     * Converts a LocationModel to a LocationEntity.
     *
     * @param model The LocationModel to convert.
     * @return The converted LocationEntity.
     */
    @Mapping(target = "id", ignore = true)
    LocationEntity toEntity(LocationModel model);

    /**
     * Converts a LocationEntity to a LocationModel.
     *
     * @param entity The LocationEntity to convert.
     * @return The converted LocationModel.
     */
    @Mapping(source = "city", target = "city")
    @Mapping(source = "department", target = "department")
    LocationModel toModel(LocationEntity entity);

    /**
     * Converts a LocationModel to a LocationEntity using provided CityEntity and DepartmentEntity.
     *
     * @param model      The LocationModel to convert.
     * @param city       The CityEntity to associate with the LocationEntity.
     * @param department The DepartmentEntity to associate with the LocationEntity.
     * @return The converted LocationEntity.
     */
    default LocationEntity toEntity(LocationModel model, CityEntity city, DepartmentEntity department) {
        return new LocationEntity(null, city, department);
    }
}