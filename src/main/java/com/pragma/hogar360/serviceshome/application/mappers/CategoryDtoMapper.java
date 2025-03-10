package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper interface for converting between Category DTOs and Category domain models.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {

    /**
     * Converts a SaveCategoryRequest DTO to a CategoryModel domain model.
     *
     * @param saveCategoryRequest The SaveCategoryRequest DTO to convert.
     * @return The corresponding CategoryModel domain model.
     */
    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);

    /**
     * Converts a CategoryModel domain model to a CategoryResponse DTO.
     *
     * @param categoryModel The CategoryModel domain model to convert.
     * @return The corresponding CategoryResponse DTO.
     */
    CategoryResponse modelToResponse(CategoryModel categoryModel);

    /**
     * Converts a list of CategoryModel domain models to a list of CategoryResponse DTOs.
     *
     * @param categories The list of CategoryModel domain models to convert.
     * @return The corresponding list of CategoryResponse DTOs.
     */
    List<CategoryResponse> modelListToResponseList(List<CategoryModel> categories);
}