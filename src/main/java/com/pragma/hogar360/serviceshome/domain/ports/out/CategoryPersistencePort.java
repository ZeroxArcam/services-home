package com.pragma.hogar360.serviceshome.domain.ports.out;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;

public interface CategoryPersistencePort {

    void save(CategoryModel categoryModel);
    CategoryModel getCategoryByName(String categoryName);
    Pagination<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}