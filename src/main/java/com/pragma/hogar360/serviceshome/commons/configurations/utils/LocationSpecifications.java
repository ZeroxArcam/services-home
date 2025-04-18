package com.pragma.hogar360.serviceshome.commons.configurations.utils;
import org.springframework.data.jpa.domain.Specification;
import com.pragma.hogar360.serviceshome.infrastructure.entities.LocationEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LocationSpecifications {

    public static Specification<LocationEntity> filterByCityAndDepartment(String cityName, String departmentName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Joins con city y department
            Join<?, ?> cityJoin = root.join("city");
            Join<?, ?> departmentJoin = root.join("department");

            if (cityName != null && !cityName.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(cityJoin.get("name")),
                        "%" + cityName.toLowerCase() + "%"
                ));
            }

            if (departmentName != null && !departmentName.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(departmentJoin.get("name")),
                        "%" + departmentName.toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
