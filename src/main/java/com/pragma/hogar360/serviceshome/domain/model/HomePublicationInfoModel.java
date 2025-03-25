package com.pragma.hogar360.serviceshome.domain.model;

import java.time.LocalDate;

public class HomePublicationInfoModel {
    private LocalDate activePublicationDate;
    private String publicationStatus;
    private LocalDate publicationDate;

    public HomePublicationInfoModel(LocalDate activePublicationDate, String publicationStatus, LocalDate publicationDate) {
        this.activePublicationDate = activePublicationDate;
        this.publicationStatus = publicationStatus;
        this.publicationDate = publicationDate;
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
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

}
