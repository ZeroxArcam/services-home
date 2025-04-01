package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Auxiliary;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;
import com.pragma.hogar360.serviceshome.domain.model.HomeQueryModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.domain.utils.constants.Pagination;
import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.HomeEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.HomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HomePersistenceAdapter implements HomePersistencePort {

    private final HomeRepository homeRepository;
    private final HomeEntityMapper homeEntityMapper;

    @Override
    public void save(HomeModel homeModel){
        homeRepository.save(homeEntityMapper.toEntity(homeModel));
    }
    @Override
    public boolean existsAddress(String address) {
        return homeRepository.existsByAddress(address);
    }

    @Override
    public List<HomeModel> findHomesToActivate(LocalDate date, HomePublicationInfoModel.PublicationStatus domainStatus) {
        HomeEntity.PublicationStatus entityStatus = homeEntityMapper.mapPublicationStatus(domainStatus);
        return homeRepository.findByActivePublicationDateAndPublicationStatus(date, entityStatus)
                .stream()
                .map(homeEntityMapper::toModel)
                .toList();
    }

    @Override
    public void saveAll(List<HomeModel> homes) {
        List<HomeEntity> entities = homes.stream()
                .map(homeEntityMapper::toEntity)
                .toList();

        homeRepository.saveAll(entities);
    }

    @Override
    public Pagination<HomeModel> findHomesByFilters(
            HomeQueryModel queryModel,
            Integer page,
            Integer size,
            String sortBy,
            String sortDirection
    ) {
        Sort sort = Auxiliary.createHomeSort(sortBy, sortDirection);
        Pageable pageable = PageRequest.of(page, size, sort);

        log.info("Fetching homes from DB - Page: {}, Size: {}, SortBy: {}, SortDirection: {}, Query: {}",
                page, size, sortBy, sortDirection, queryModel);

        Page<HomeEntity> homePages = homeRepository.findFilteredHomes(
                queryModel.getCurrentDate(),
                queryModel.getLocationId(),
                queryModel.getCategoryId(),
                queryModel.getMinRooms(),
                queryModel.getMaxRooms(),
                queryModel.getMinBathrooms(),
                queryModel.getMaxBathrooms(),
                queryModel.getMinPrice(),
                queryModel.getMaxPrice(),
                pageable
        );

        log.info("Homes fetched. Total Elements: {}, Total Pages: {}",
                homePages.getTotalElements(), homePages.getTotalPages());

        Pagination<HomeModel> result = homeEntityMapper.homeEntityPageToHomeModelPagination(homePages);

        log.info("Mapped Homes: {}", result.getItems());
        log.info("Pagination mapped successfully. Returning response...");

        return result;
    }

}