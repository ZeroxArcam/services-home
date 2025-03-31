package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;

import java.time.LocalDate;
import java.util.List;

public interface HomePersistencePort {
    void save(HomeModel homeModel);
    boolean existsAddress(String address);
    void saveAll(List<HomeModel> homes);

    List<HomeModel> findHomesToActivate(
            LocalDate date,
            HomePublicationInfoModel.PublicationStatus status
    );

}