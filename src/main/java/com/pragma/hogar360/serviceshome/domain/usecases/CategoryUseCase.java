package com.pragma.hogar360.serviceshome.domain.usecases;
import com.pragma.hogar360.serviceshome.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Validation;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(CategoryModel categoryModel) {
        Validation.validateName(categoryModel.getName());
        Validation.validateDescription(categoryModel.getDescription());
        CategoryModel category = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (category != null) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public Pagination<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        Validation.validatePageAndSize(page,size);
        return categoryPersistencePort.getCategories(page, size, orderAsc);
    }

    @Override
    public CategoryModel getCategoryByName(String name) {
        return categoryPersistencePort.getCategoryByName(name);
    }
}