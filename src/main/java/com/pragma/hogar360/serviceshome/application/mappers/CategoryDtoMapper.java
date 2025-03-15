package com.pragma.hogar360.serviceshome.application.mappers;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
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
     * Converts a Pagination<CategoryModel> domain model to a PagedCategoryResponse DTO.
     *
     * @param categoryPage The Pagination<CategoryModel> domain model containing paginated categories and metadata.
     * @return The corresponding PagedCategoryResponse DTO.
     */
    PagedCategoryResponse modelPageToPagedResponse(Pagination<CategoryModel> categoryPage);
}