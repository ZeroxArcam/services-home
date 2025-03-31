package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.HomeBasicInfoModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeDetailsModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;

public class HomeModelFactory {

    public static HomeModel createHomeModel(Long id, HomeBasicInfoModel basicInfo, HomeDetailsModel propertyDetails, HomePublicationInfoModel publicationInfo) {
        return new HomeModel(id, basicInfo, propertyDetails, publicationInfo);
    }

    public static HomeModel createDefaultHomeModel() {
        return createHomeModel(
                1L,
                HomeBasicInfoModelFactory.createDefaultHomeBasicInfoModel(),
                HomeDetailsModelFactory.createDefaultHomeDetailsModel(),
                HomePublicationInfoModelFactory.createDefaultHomePublicationInfoModel()
        );
    }

    public static HomeModel createHomeModelWithBasicInfo(HomeBasicInfoModel basicInfo) {
        return createHomeModel(
                1L,
                basicInfo,
                HomeDetailsModelFactory.createDefaultHomeDetailsModel(),
                HomePublicationInfoModelFactory.createDefaultHomePublicationInfoModel()
        );
    }

    public static HomeModel createHomeModelWithDetails(HomeDetailsModel propertyDetails) {
        return createHomeModel(
                1L,
                HomeBasicInfoModelFactory.createDefaultHomeBasicInfoModel(),
                HomeDetailsModelFactory.createHomeDetailsModel(propertyDetails.getDescription(), propertyDetails.getCategory(), propertyDetails.getNumberOfRooms(), propertyDetails.getNumberOfBathrooms()),
                HomePublicationInfoModelFactory.createDefaultHomePublicationInfoModel()
        );
    }

    public static HomeModel createHomeModelWithPublicationInfo(HomePublicationInfoModel publicationInfo) {
        return createHomeModel(
                1L,
                HomeBasicInfoModelFactory.createDefaultHomeBasicInfoModel(),
                HomeDetailsModelFactory.createDefaultHomeDetailsModel(),
                publicationInfo
        );
    }

    public static HomeModel createHomeModelWithId(Long id) {
        return createHomeModel(
                id,
                HomeBasicInfoModelFactory.createDefaultHomeBasicInfoModel(),
                HomeDetailsModelFactory.createDefaultHomeDetailsModel(),
                HomePublicationInfoModelFactory.createDefaultHomePublicationInfoModel()
        );
    }
}