package com.pragma.hogar360.serviceshome.infrastructure.mappers;

import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

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
     * Converts a list of CategoryEntity objects to a list of CategoryModel objects.
     *
     * @param categories The list of CategoryEntity objects to convert.
     * @return A list of CategoryModel objects representing the converted entities.
     */
    List<CategoryModel> categoryEntityListToCategoryModelList(List<CategoryEntity> categories);
}