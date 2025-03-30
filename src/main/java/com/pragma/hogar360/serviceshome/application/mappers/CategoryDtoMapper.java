package com.pragma.hogar360.serviceshome.application.mappers;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {

    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);
    CategoryResponse modelToResponse(CategoryModel categoryModel);
    PagedCategoryResponse modelPageToPagedResponse(Pagination<CategoryModel> categoryPage);
}