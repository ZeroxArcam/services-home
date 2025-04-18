package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.entities.DepartmentEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.DepartmentEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DepartmentPersistenceAdapter implements DepartmentPersistencePort {

    private final DepartmentRepository departmentRepository;
    private final DepartmentEntityMapper departmentEntityMapper;

    @Override
    public void save(DepartmentModel departmentModel){
        departmentRepository.save(departmentEntityMapper.toEntity(departmentModel));
    }

    @Override
    public boolean existsByName(String name) {
        log.info("Checking if department exists by name: {}", name);
        boolean exists = departmentRepository.existsByName(name);
        log.info("Department exists: {}", exists);
        return exists;
    }

    @Override
    public Optional<DepartmentModel> findByName(String name) {
        log.info("Fetching department by name: {}", name);
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findByName(name);
        log.info("Department found: {}", departmentEntity.isPresent());
        return departmentEntity.map(departmentEntityMapper::toModel);
    }
}