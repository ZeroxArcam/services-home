package com.pragma.hogar360.serviceshome.commons.configurations.utils;

import com.pragma.hogar360.serviceshome.domain.exceptions.InvalidParameters;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class Auxiliary {
    private Auxiliary() {}

    public static Sort createSort(String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        List<Sort.Order> orders = new ArrayList<>();
        String[] sortFields = sortBy.split(",");

        for (String field : sortFields) {
            String trimmedField = field.trim();

            switch (trimmedField) {
                case "cityName":
                    orders.add(new Sort.Order(direction, "city.name"));
                    break;
                case "departmentName":
                    orders.add(new Sort.Order(direction, "city.department.name"));
                    break;
                default:
                    throw new InvalidParameters("Invalid sortBy value: " + field +
                            ". Use 'departmentName', 'cityName', or both separated by a comma.");
            }
        }

        return Sort.by(orders);
    }

    public static Sort createHomeSort(String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        List<Sort.Order> orders = new ArrayList<>();
        String[] sortFields = sortBy.split(",");

        for (String field : sortFields) {
            String trimmedField = field.trim();

            switch (trimmedField) {
                case "price":
                    orders.add(new Sort.Order(direction, "price"));
                    break;
                case "numberOfRooms":
                    orders.add(new Sort.Order(direction, "numberOfRooms"));
                    break;
                case "numberOfBathrooms":
                    orders.add(new Sort.Order(direction, "numberOfBathrooms"));
                    break;
                case "locationId":
                    orders.add(new Sort.Order(direction, "location.id"));
                    break;
                case "categoryId":
                    orders.add(new Sort.Order(direction, "category.id"));
                    break;
                default:
                    throw new InvalidParameters("Invalid sortBy value: " + field +
                            ". Use 'price', 'numberOfRooms', 'numberOfBathrooms', 'locationId', 'categoryName', 'activePublicationDate', 'publicationDate', or any combination separated by a comma.");
            }
        }

        return Sort.by(orders);
    }
}