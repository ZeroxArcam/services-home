package com.pragma.hogar360.serviceshome.infrastructure.mappers;
import com.pragma.hogar360.serviceshome.domain.model.LocationModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationEntityMapper {

    //@Mapping(target = "id", ignore = true)
    @Mapping(source ="id",target = "id")
    @Mapping(source = "neighborhood",target = "neighborhood")
    @Mapping(source = "city", target = "city")
    LocationEntity toEntity(LocationModel model);

    @Mapping(source ="id",target = "id")
    @Mapping(source = "neighborhood",target = "neighborhood")
    @Mapping(source = "city", target = "city")
    LocationModel toModel(LocationEntity entity);

    @Mapping(target = "items", expression = "java(locationPage.getContent().stream().map(this::toModel).toList())")
    @Mapping(target = "totalElements", expression = "java(locationPage.getTotalElements())")
    @Mapping(target = "totalPages", expression = "java(locationPage.getTotalPages())")
    @Mapping(target = "pageNumber", expression = "java(locationPage.getNumber())")
    @Mapping(target = "pageSize", expression = "java(locationPage.getSize())")
    Pagination<LocationModel> locationEntityPageToLocationModelPagination(Page<LocationEntity> locationPage);

}