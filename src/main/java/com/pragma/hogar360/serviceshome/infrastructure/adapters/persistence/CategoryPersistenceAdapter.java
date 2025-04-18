package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CategoryEntity;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void save(CategoryModel categoryModel) {
        categoryRepository.save(categoryEntityMapper.categoryModelToCategoryEntity(categoryModel));
    }

    @Override
    public CategoryModel getCategoryByName(String categoryName) {
        return categoryEntityMapper.categoryEntityToCategoryModel(categoryRepository.findByName(categoryName).orElse(null));
    }

    @Override
    public Pagination<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) {
            pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        }

        log.info("Fetching categories from DB - Page: {}, Size: {}, OrderAsc: {}", page, size, orderAsc);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pagination);
        log.info("Categories fetched. Total Elements: {}, Total Pages: {}", categoryPage.getTotalElements(), categoryPage.getTotalPages());

        Pagination<CategoryModel> result = categoryEntityMapper.categoryEntityPageToCategoryModelPagination(categoryPage);
        log.info("Mapped Categories: {}", result.getItems());

        log.info("Pagination mapped successfully. Returning response...");
        return result;
    }

}