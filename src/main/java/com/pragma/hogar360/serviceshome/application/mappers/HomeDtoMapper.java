package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeDtoMapper {


//    String nombre,
//    String description,
//    String category,
//    Integer numberOfRooms,
//    Integer numberOfBathrooms,
//    Double price,
//    String city,
//    String department,
//    LocalDate activePublicationDate,
//    String publicationStatus) {}


    @Mapping(target = "basicInfo", source = ".")
    @Mapping(target = "propertyDetails", source = ".")
    @Mapping(target = "publicationInfo", source = ".")
    HomeModel requestToModel(SaveHomeRequest homeModel);

//    private String name;
//    private Double price;
//    private LocationModel location;
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(source = "department", target = "location.department.name")
    @Mapping(source = "city", target = "location.city.name")
    HomeBasicInfoModel mapBasicInfo(SaveHomeRequest homeModel);


//    private String description;
//    private CategoryModel category;
//    private Integer numberOfRooms;
//    private Integer numberOfBathrooms;
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category.name", source = "category")
    @Mapping(target = "numberOfRooms", source = "numberOfRooms")
    @Mapping(target = "numberOfBathrooms", source = "numberOfBathrooms")
    HomeDetailsModel mapDetails(SaveHomeRequest homeModel);

//    private LocalDate activePublicationDate;
//    private String publicationStatus;

    @Mapping(target = "activePublicationDate", source = "activePublicationDate")
    @Mapping(target = "publicationStatus", source = "publicationStatus")
    @Mapping(target = "publicationDate" , source = "publicationDate")
    HomePublicationInfoModel mapPublicationInfo(SaveHomeRequest homeModel);
}