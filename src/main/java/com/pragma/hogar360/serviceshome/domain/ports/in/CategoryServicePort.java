package com.pragma.hogar360.serviceshome.domain.ports.in;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

public interface CategoryServicePort {

    void save(CategoryModel categoryModel);
    Pagination<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
    CategoryModel getCategoryByName(String name);
}