package com.pragma.hogar360.serviceshome.factory;

import com.pragma.hogar360.serviceshome.domain.model.HomeQueryModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HomeQueryModelFactory {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long DEFAULT_HOME_ID = null; // O un valor por defecto si tiene sentido

    public static HomeQueryModel createDefaultHomeQueryModel() {
        return new HomeQueryModel(
                1L, // locationId
                1L, // categoryId
                1,  // minRooms
                5,  // maxRooms
                1,  // minBathrooms
                3,  // maxBathrooms
                BigDecimal.valueOf(100000), // minPrice
                BigDecimal.valueOf(500000), // maxPrice
                LocalDate.now(), // currentDate
                DEFAULT_USER_ID, // userId
                DEFAULT_HOME_ID  // homeId
        );
    }

    public static HomeQueryModel createHomeQueryModelWithMinRooms(int minRooms) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setMinRooms(minRooms);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithMaxRooms(int maxRooms) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setMaxRooms(maxRooms);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithMinBathrooms(int minBathrooms) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setMinBathrooms(minBathrooms);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithMaxBathrooms(int maxBathrooms) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setMaxBathrooms(maxBathrooms);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithMinPrice(BigDecimal minPrice) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setMinPrice(minPrice);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithMaxPrice(BigDecimal maxPrice) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setMaxPrice(maxPrice);
        return model;
    }

    // Nuevos métodos factory para incluir userId y homeId si es necesario en algunos tests

    public static HomeQueryModel createHomeQueryModelWithUserId(Long userId) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setUserId(userId);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithHomeId(Long homeId) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setHomeId(homeId);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithUserAndHomeId(Long userId, Long homeId) {
        HomeQueryModel model = createDefaultHomeQueryModel();
        model.setUserId(userId);
        model.setHomeId(homeId);
        return model;
    }

    public static HomeQueryModel createHomeQueryModelWithAllParams(Long locationId, Long categoryId, Integer minRooms, Integer maxRooms, Integer minBathrooms, Integer maxBathrooms, BigDecimal minPrice, BigDecimal maxPrice, LocalDate currentDate, Long userId, Long homeId) {
        return new HomeQueryModel(locationId, categoryId, minRooms, maxRooms, minBathrooms, maxBathrooms, minPrice, maxPrice, currentDate, userId, homeId);
    }
}