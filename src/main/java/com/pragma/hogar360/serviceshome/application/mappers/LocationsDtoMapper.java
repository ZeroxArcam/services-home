package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface for converting between location DTOs and domain models.
 * Utilizes MapStruct for automatic mapping code generation.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.1
 * @since [16/3/2025]
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationsDtoMapper {

    /**
     * Converts a {@link SaveLocationRequest} object to a {@link LocationModel} object.
     *
     * @param locationModel The request DTO to convert.
     * @return The resulting domain model.
     */
    LocationModel requestToModel(SaveLocationRequest locationModel);

    /**
     * Converts a {@link LocationModel} object to a {@link LocationResponse} object.
     * This method provides a custom implementation for the conversion.
     *
     * @param locationModel The domain model to convert.
     * @return The resulting response DTO.
     */
    default LocationResponse modelToResponse(LocationModel locationModel) {
        return new LocationResponse(
                locationModel.getId(),
                locationModel.getCity().getName(),
                locationModel.getDepartment().getName()
        );
    }

    /**
     * Converts a paginated list of {@link LocationModel} objects to a {@link PagedLocationResponse}.
     *
     * @param locationPage The paginated domain model.
     * @return The resulting paginated response DTO.
     */
    PagedLocationResponse modelPageToPagedResponse(Pagination<LocationModel> locationPage);
}