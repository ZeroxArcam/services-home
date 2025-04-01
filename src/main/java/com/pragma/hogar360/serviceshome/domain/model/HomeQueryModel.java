package com.pragma.hogar360.serviceshome.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HomeQueryModel {
    private Long locationId;
    private Long categoryId;
    private Integer minRooms;
    private Integer maxRooms;
    private Integer minBathrooms;
    private Integer maxBathrooms;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private LocalDate currentDate;

    public HomeQueryModel() {}

    public HomeQueryModel(Long locationId, Long categoryId, Integer minRooms, Integer maxRooms, Integer minBathrooms, Integer maxBathrooms, BigDecimal minPrice, BigDecimal maxPrice, LocalDate currentDate) {
        this.locationId = locationId;
        this.categoryId = categoryId;
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
        this.minBathrooms = minBathrooms;
        this.maxBathrooms = maxBathrooms;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.currentDate = currentDate;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMinRooms() {
        return minRooms;
    }

    public void setMinRooms(Integer minRooms) {
        this.minRooms = minRooms;
    }

    public Integer getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(Integer maxRooms) {
        this.maxRooms = maxRooms;
    }

    public Integer getMinBathrooms() {
        return minBathrooms;
    }

    public void setMinBathrooms(Integer minBathrooms) {
        this.minBathrooms = minBathrooms;
    }

    public Integer getMaxBathrooms() {
        return maxBathrooms;
    }

    public void setMaxBathrooms(Integer maxBathrooms) {
        this.maxBathrooms = maxBathrooms;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}