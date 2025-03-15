package com.pragma.hogar360.serviceshome.infrastructure.mappers;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
/**
 * Mapper interface for converting between CategoryEntity and CategoryModel.
 * Uses MapStruct for automatic code generation.
 */
@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    /**
     * Converts a CategoryEntity to a CategoryModel.
     *
     * @param categoryEntity The CategoryEntity to convert.
     * @return The corresponding CategoryModel.
     */
    CategoryModel categoryEntityToCategoryModel(CategoryEntity categoryEntity);

    /**
     * Converts a CategoryModel to a CategoryEntity.
     *
     * @param categoryModel The CategoryModel to convert.
     * @return The corresponding CategoryEntity.
     */
    CategoryEntity categoryModelToCategoryEntity(CategoryModel categoryModel);

    /**
     * Converts a Page of CategoryEntity objects to a Pagination of CategoryModel objects.
     *
     * @param categoryPage The Page of CategoryEntity objects to convert.
     * @return A Pagination of CategoryModel objects representing the converted entities and pagination metadata.
     */
    @Mapping(target = "items", expression = "java(categoryPage.getContent().stream().map(this::categoryEntityToCategoryModel).toList())")
    @Mapping(target = "totalElements", expression = "java(categoryPage.getTotalElements())")
    @Mapping(target = "totalPages", expression = "java(categoryPage.getTotalPages())")
    @Mapping(target = "pageNumber", expression = "java(categoryPage.getNumber())")
    @Mapping(target = "pageSize", expression = "java(categoryPage.getSize())")

    Pagination<CategoryModel> categoryEntityPageToCategoryModelPagination(Page<CategoryEntity> categoryPage);
}
