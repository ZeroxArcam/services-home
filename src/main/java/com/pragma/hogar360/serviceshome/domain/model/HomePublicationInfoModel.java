package com.pragma.hogar360.serviceshome.domain.model;

import java.time.LocalDate;
public class HomePublicationInfoModel {
    public enum PublicationStatus {
        PUBLISHED,
        PUBLICATION_PAUSED,
        TRANSACTION_IN_PROGRESS,
        TRANSACTION_COMPLETED
    }

    private LocalDate activePublicationDate;
    private PublicationStatus publicationStatus;
    private LocalDate publicationDate;

    public HomePublicationInfoModel(LocalDate activePublicationDate,
                                    PublicationStatus publicationStatus,
                                    LocalDate publicationDate) {
        this.activePublicationDate = activePublicationDate;
        this.publicationStatus = publicationStatus;
        this.publicationDate = publicationDate;
    }

    public PublicationStatus getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(PublicationStatus publicationStatus) {
        this.publicationStatus = publicationStatus;
    }
    public LocalDate getActivePublicationDate() {
        return activePublicationDate;
    }


    public void setActivePublicationDate(LocalDate activePublicationDate) {
        this.activePublicationDate = activePublicationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

}