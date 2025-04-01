package com.pragma.hogar360.serviceshome.infrastructure.mappers;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface HomeEntityMapper {

    @Mapping(target = "id",source = "id")
    @Mapping(target = "basicInfo.name", source = "name")
    @Mapping(target = "basicInfo.address", source = "address")
    @Mapping(target = "basicInfo.price", source = "price")
    @Mapping(target = "basicInfo.location", source = "location")
    @Mapping(target = "propertyDetails.description", source = "description")
    @Mapping(target = "propertyDetails.category", source = "category")
    @Mapping(target = "propertyDetails.numberOfRooms", source = "numberOfRooms")
    @Mapping(target = "propertyDetails.numberOfBathrooms", source = "numberOfBathrooms")
    @Mapping(target = "publicationInfo.activePublicationDate", source = "activePublicationDate")
    @Mapping(target = "publicationInfo.publicationStatus", source = "publicationStatus")
    @Mapping(target = "publicationInfo.publicationDate", source = "publicationDate")
    HomeModel toModel(HomeEntity homeEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "basicInfo.name")
    @Mapping(target = "address", source ="basicInfo.address")
    @Mapping(target = "price", source = "basicInfo.price")
    @Mapping(target = "location", source = "basicInfo.location")
    @Mapping(target = "description", source = "propertyDetails.description")
    @Mapping(target = "category", source = "propertyDetails.category")
    @Mapping(target = "numberOfRooms", source = "propertyDetails.numberOfRooms")
    @Mapping(target = "numberOfBathrooms", source = "propertyDetails.numberOfBathrooms")
    @Mapping(target = "activePublicationDate", source = "publicationInfo.activePublicationDate")
    @Mapping(target = "publicationStatus", source = "publicationInfo.publicationStatus")
    @Mapping(target = "publicationDate", source = "publicationInfo.publicationDate")
    HomeEntity toEntity(HomeModel homeModel);

    default HomeEntity.PublicationStatus mapPublicationStatus(HomePublicationInfoModel.PublicationStatus status) {
        return HomeEntity.PublicationStatus.valueOf(status.name());
    }

    default HomePublicationInfoModel.PublicationStatus mapPublicationStatus(HomeEntity.PublicationStatus status) {
        return HomePublicationInfoModel.PublicationStatus.valueOf(status.name());
    }
    @Mapping(target = "items", expression = "java(homePage.getContent().stream().map(this::toModel).toList())")
    @Mapping(target = "totalElements", expression = "java(homePage.getTotalElements())")
    @Mapping(target = "totalPages", expression = "java(homePage.getTotalPages())")
    @Mapping(target = "pageNumber", expression = "java(homePage.getNumber())")
    @Mapping(target = "pageSize", expression = "java(homePage.getSize())")
    Pagination<HomeModel> homeEntityPageToHomeModelPagination(Page<HomeEntity> homePage);

}