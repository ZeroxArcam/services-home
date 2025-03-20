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
                    orders.add(new Sort.Order(direction, "department.name"));
                    break;
                default:
                    throw new InvalidParameters("Invalid sortBy value: " + field +
                            ". Use 'department', 'city', or both separated by a comma.");
            }
        }

        return Sort.by(orders);
    }
}
