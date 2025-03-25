package com.pragma.hogar360.serviceshome.infrastructure.mappers;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.*;
import org.mapstruct.Mapper;
import com.pragma.hogar360.serviceshome.domain.model.*;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeEntityMapper {

    @Mapping(target = "basicInfo", source = ".")
    @Mapping(target = "propertyDetails", source = ".")
    @Mapping(target = "publicationInfo", source = ".")
    HomeModel toModel(HomeEntity homeEntity);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "location", source = "location")
    HomeBasicInfoModel mapBasicInfo(HomeEntity homeEntity);

    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "numberOfRooms", source = "numberOfRooms")
    @Mapping(target = "numberOfBathrooms", source = "numberOfBathrooms")
    HomeDetailsModel mapDetails(HomeEntity homeEntity);

    @Mapping(target = "activePublicationDate", source = "activePublicationDate")
    @Mapping(target = "publicationStatus", source = "publicationStatus")
    HomePublicationInfoModel mapPublicationInfo(HomeEntity homeEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "basicInfo.name")
    @Mapping(target = "price", source = "basicInfo.price")
    @Mapping(target = "location", source = "basicInfo.location")
    @Mapping(target = "description", source = "propertyDetails.description")
    @Mapping(target = "category", source = "propertyDetails.category")
    @Mapping(target = "numberOfRooms", source = "propertyDetails.numberOfRooms")
    @Mapping(target = "numberOfBathrooms", source = "propertyDetails.numberOfBathrooms")
    @Mapping(target = "activePublicationDate", source = "publicationInfo.activePublicationDate")
    @Mapping(target = "publicationStatus", source = "publicationInfo.publicationStatus")
    @Mapping(target = "publicationDate", source = "publicationInfo.publicationDate") // Corregido
    HomeEntity toEntity(HomeModel homeModel);

//    default HomeEntity toEntity(HomeModel model, CategoryEntity category, LocationEntity location) {
//        return new HomeEntity(,);
//    }
}