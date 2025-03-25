package com.pragma.hogar360.serviceshome.domain.model;

import java.time.LocalDate;

public class HomePublicationInfoModel {
    private LocalDate activePublicationDate;
    private String publicationStatus;

    public HomePublicationInfoModel(LocalDate activePublicationDate, String publicationStatus) {
        this.activePublicationDate = activePublicationDate;
        this.publicationStatus = publicationStatus;
    }
    public HomePublicationInfoModel() {}

    public LocalDate getActivePublicationDate() {
        return activePublicationDate;
    }

    public String getPublicationStatus() {
        return publicationStatus;
    }

    public void setActivePublicationDate(LocalDate activePublicationDate) {
        this.activePublicationDate = activePublicationDate;
    }

    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }
}
