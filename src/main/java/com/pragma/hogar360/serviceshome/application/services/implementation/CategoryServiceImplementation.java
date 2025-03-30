package com.pragma.hogar360.serviceshome.application.services.implementation;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCategoryRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedCategoryResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCategoryResponse;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.application.dto.response.CategoryResponse;
import com.pragma.hogar360.serviceshome.application.mappers.CategoryDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.CategoryService;
import com.pragma.hogar360.serviceshome.domain.model.CategoryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public SaveCategoryResponse save(SaveCategoryRequest request) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(request));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public PagedCategoryResponse getCategories(Integer page, Integer size, boolean orderAsc) {
        Pagination<CategoryModel> categoryPagination = categoryServicePort.getCategories(page, size, orderAsc);
        List<CategoryResponse> categoryResponses = categoryPagination.getItems().stream()
                .map(categoryDtoMapper::modelToResponse)
                .toList();

        return new PagedCategoryResponse(
                categoryResponses, // converted list
                categoryPagination.getTotalElements(),
                categoryPagination.getTotalPages(),
                categoryPagination.getPageNumber(),
                categoryPagination.getPageSize()
        );
    }
    @Override
    public CategoryResponse getCategoryByName(String name) {
        return categoryDtoMapper.modelToResponse(categoryServicePort.getCategoryByName(name));
    }
}