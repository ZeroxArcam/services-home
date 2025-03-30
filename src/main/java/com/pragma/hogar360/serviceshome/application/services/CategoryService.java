package com.pragma.hogar360.serviceshome.application.services;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;

public interface CategoryService {

    SaveCategoryResponse save(SaveCategoryRequest request);
    PagedCategoryResponse getCategories(Integer page, Integer size, boolean orderAsc);
    CategoryResponse getCategoryByName(String name);
}