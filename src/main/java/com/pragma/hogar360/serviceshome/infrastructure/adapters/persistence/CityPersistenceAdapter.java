package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CityEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityPersistenceAdapter implements CityPersistencePort {

    private final CityRepository cityRepository;
    private final CityEntityMapper cityEntityMapper;

    @Override
    public void save(CityModel cityModel) {
        cityRepository.save(cityEntityMapper.toEntity(cityModel));
    }

    @Override
    public boolean existsByNameAndDepartmentName(String name, String department){
       return cityRepository.existsByNameAndDepartmentName(name, department);
    }

    @Override
    public boolean existsById(Long id){
        return cityRepository.existsById(id);
    }
}