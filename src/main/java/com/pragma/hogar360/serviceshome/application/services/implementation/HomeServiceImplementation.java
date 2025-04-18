package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.appconstants.Constants;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.HomeResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.PagedHomeResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveHomeResponse;
import com.pragma.hogar360.serviceshome.application.mappers.HomeDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.HomeService;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeQueryModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImplementation implements HomeService {
    private final HomeDtoMapper homeDtoMapper;
    private final HomeServicePort homeServicePort;

    @Override
    public SaveHomeResponse save(SaveHomeRequest request, Long userId){
        HomeModel homeModel = homeDtoMapper.requestToModel(request);
        homeModel.setUserId(userId);
        homeServicePort.save(homeModel);
        return new SaveHomeResponse(Constants.SAVE_HOME_RESPONSE_MESSAGE, LocalDateTime.now());
    }
    @Override
    public PagedHomeResponse findHomesByFilters(HomeQueryModel queryModel, Integer page, Integer size, String sortBy, String sortDirection) {
        Pagination<HomeModel> homePagination = homeServicePort.findHomesByFilters(queryModel, page, size, sortBy, sortDirection);
        List<HomeResponse> homeResponses = homePagination.getItems().stream()
                .map(homeDtoMapper::modelToResponse)
                .toList();

        return new PagedHomeResponse(
                homeResponses,
                homePagination.getTotalElements(),
                homePagination.getTotalPages(),
                homePagination.getPageNumber(),
                homePagination.getPageSize()
        );
    }

}