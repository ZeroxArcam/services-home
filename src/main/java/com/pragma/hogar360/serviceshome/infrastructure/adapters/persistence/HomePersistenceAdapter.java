package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.HomeEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.HomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


//
//    @Override
//    public HomeModel saveHome(HomeModel homeModel){
//        HomeEntity homeEntity = homeEntityMapper.toEntity(homeModel);
//        // Guarda usando el repositorio
//        HomeEntity savedEntity = homeRepository.save(homeEntity);
//        // Convierte de vuelta a HomeModel
//        return homeEntityMapper.toModel(savedEntity);
//    }
//



//    @Override
//    public boolean existById(HomeModel homeModel){
//        return homeRepository.existsById(homeModel.getId());
//    }

}
