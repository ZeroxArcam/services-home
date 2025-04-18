package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel.PublicationStatus;

import java.time.LocalDate;

public class HomePublicationInfoModelFactory {

    public static HomePublicationInfoModel createHomePublicationInfoModel(LocalDate activePublicationDate, PublicationStatus publicationStatus, LocalDate publicationDate) {
        return new HomePublicationInfoModel(activePublicationDate, publicationStatus, publicationDate);
    }

    public static HomePublicationInfoModel createDefaultHomePublicationInfoModel() {
        return createHomePublicationInfoModel(LocalDate.now().plusDays(7), PublicationStatus.PUBLISHED, LocalDate.now());
    }

    public static HomePublicationInfoModel createHomePublicationInfoModelWithActiveDate(LocalDate activePublicationDate) {
        return createHomePublicationInfoModel(activePublicationDate, PublicationStatus.PUBLISHED, LocalDate.now());
    }

    public static HomePublicationInfoModel createHomePublicationInfoModelWithStatus(PublicationStatus publicationStatus) {
        return createHomePublicationInfoModel(LocalDate.now().plusDays(7), publicationStatus, LocalDate.now());
    }

    public static HomePublicationInfoModel createHomePublicationInfoModelWithPublicationDate(LocalDate publicationDate) {
        return createHomePublicationInfoModel(LocalDate.now().plusDays(7), PublicationStatus.PUBLISHED, publicationDate);
    }
}