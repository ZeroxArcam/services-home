package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.HomeResponse;
import com.pragma.hogar360.serviceshome.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeDtoMapper {

    @Mapping(target = "basicInfo.name", source = "name")
    @Mapping(target = "basicInfo.address", source = "address")
    @Mapping(target = "basicInfo.price", source = "price")
    @Mapping(target = "basicInfo.location.id", source = "cityId")
    @Mapping(target = "propertyDetails.description", source = "description")
    @Mapping(target = "propertyDetails.category.name", source = "category")
    @Mapping(target = "propertyDetails.numberOfRooms", source = "numberOfRooms")
    @Mapping(target = "propertyDetails.numberOfBathrooms", source = "numberOfBathrooms")
    @Mapping(target = "publicationInfo.activePublicationDate", source = "activePublicationDate")
    //@Mapping(target = "publicationInfo.publicationStatus", source = "publicationStatus")
    @Mapping(target = "publicationInfo.publicationDate", source = "publicationDate")
    HomeModel requestToModel(SaveHomeRequest homeModel);

    //Long id,
    //                           String neighborhood,
    //                           String address,
    //                           String description,
    //                           String category,
    //                           Integer numberOfRooms,
    //                           Integer numberOfBathrooms,
    //                           Double price,
    //                           //Long cityId,
    //                           String cityName,
    //                           String departmentName,
    //                           LocalDate activePublicationDate
    //                           ){}


    default HomeResponse modelToResponse(HomeModel homeModel) {
        return new HomeResponse(
                homeModel.getId(),
                homeModel.getBasicInfo().getLocation().getNeighborhood(),
                homeModel.getBasicInfo().getAddress(),
                homeModel.getPropertyDetails().getDescription(),
                homeModel.getPropertyDetails().getCategory().getName(),
                homeModel.getPropertyDetails().getNumberOfRooms(),
                homeModel.getPropertyDetails().getNumberOfBathrooms(),
                homeModel.getBasicInfo().getPrice(),
                homeModel.getBasicInfo().getLocation().getCityName(),
                homeModel.getBasicInfo().getLocation().getCity().getDepartment().getName(),
                homeModel.getPublicationInfo().getActivePublicationDate()
        );
    }
    //HomeResponse modelToResponse(HomeModel homeModel);
}