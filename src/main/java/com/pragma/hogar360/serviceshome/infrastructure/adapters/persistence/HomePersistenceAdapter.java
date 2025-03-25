package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.HomeRepository;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HomePersistenceAdapter implements HomePersistencePort {

    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final HomeEntity homeEntity;

    @Override
    public HomeModel saveHome(HomeModel homeModel){
        return homeModel;
    }

}
