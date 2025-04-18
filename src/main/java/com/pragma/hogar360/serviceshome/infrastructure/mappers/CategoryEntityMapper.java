package com.pragma.hogar360.serviceshome.infrastructure.mappers;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryModel categoryEntityToCategoryModel(CategoryEntity categoryEntity);
    CategoryEntity categoryModelToCategoryEntity(CategoryModel categoryModel);

    @Mapping(target = "items", expression = "java(categoryPage.getContent().stream().map(this::categoryEntityToCategoryModel).toList())")
    @Mapping(target = "totalElements", expression = "java(categoryPage.getTotalElements())")
    @Mapping(target = "totalPages", expression = "java(categoryPage.getTotalPages())")
    @Mapping(target = "pageNumber", expression = "java(categoryPage.getNumber())")
    @Mapping(target = "pageSize", expression = "java(categoryPage.getSize())")
    Pagination<CategoryModel> categoryEntityPageToCategoryModelPagination(Page<CategoryEntity> categoryPage);
}
