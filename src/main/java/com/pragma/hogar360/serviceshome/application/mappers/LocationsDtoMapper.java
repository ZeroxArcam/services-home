package com.pragma.hogar360.serviceshome.application.mappers;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveLocationRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.LocationResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedLocationResponse;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationsDtoMapper {
    @Mapping(source = "neighborhood", target = "neighborhood")
    @Mapping(source = "cityDepartmentId", target = "city.id")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);

    default LocationResponse modelToResponse(LocationModel locationModel) {
        return new LocationResponse(
                locationModel.getId(),
                locationModel.getCity().getName(),
                locationModel.getCity().getDepartment().getName(),
                locationModel.getNeighborhood()
        );
    }
    PagedLocationResponse modelPageToPagedResponse(Pagination<LocationModel> locationPage);
}