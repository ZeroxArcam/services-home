package com.pragma.hogar360.serviceshome.domain.model;

public class HomeModel {
    private Long id;
    private HomeBasicInfoModel basicInfo;
    private HomeDetailsModel propertyDetails;
    private HomePublicationInfoModel publicationInfo;
    private Long userId;

    public HomeModel(Long id, HomeBasicInfoModel basicInfo, HomeDetailsModel propertyDetails, HomePublicationInfoModel publicationInfo,Long userId) {
        this.id = id;
        this.basicInfo = basicInfo;
        this.propertyDetails = propertyDetails;
        this.publicationInfo = publicationInfo;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeBasicInfoModel getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(HomeBasicInfoModel basicInfo) {
        this.basicInfo = basicInfo;
    }

    public HomeDetailsModel getPropertyDetails() {
        return propertyDetails;
    }

    public void setPropertyDetails(HomeDetailsModel propertyDetails) {
        this.propertyDetails = propertyDetails;
    }

    public HomePublicationInfoModel getPublicationInfo() {
        return publicationInfo;
    }

    public void setPublicationInfo(HomePublicationInfoModel publicationInfo) {
        this.publicationInfo = publicationInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}