package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;

import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.CityPersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.entities.CityEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CityEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Adapter class for city persistence operations using JPA.
 * Implements the {@link CityPersistencePort} interface.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityPersistenceAdapter implements CityPersistencePort {

    /**
     * Repository for city entity operations.
     */
    private final CityRepository cityRepository;

    /**
     * Mapper for converting between city domain models and entities.
     */
    private final CityEntityMapper cityEntityMapper;

    /**
     * Saves a city domain model to the database.
     *
     * @param cityModel The city domain model to save.
     * @return The saved city domain model.
     */
    @Override
    public CityModel saveCity(CityModel cityModel) {
        log.info("Saving city: {}", cityModel.getName());
        CityEntity cityEntity = cityEntityMapper.toEntity(cityModel);
        CityEntity savedEntity = cityRepository.save(cityEntity);
        log.info("City saved successfully: {}", savedEntity.getName());
        return cityEntityMapper.toModel(savedEntity);
    }

    /**
     * Checks if a city with the given name exists in the database.
     *
     * @param name The name of the city to check.
     * @return {@code true} if a city with the name exists, {@code false} otherwise.
     */
    @Override
    public boolean existsByName(String name) {
        boolean exists = cityRepository.existsByName(name);
        log.info("City exists: {}", exists);
        return exists;
    }

    /**
     * Finds a city domain model by its name in the database.
     *
     * @param name The name of the city to find.
     * @return An {@link Optional} containing the city domain model, or an empty {@link Optional} if not found.
     */
    @Override
    public Optional<CityModel> findByName(String name) {
        Optional<CityEntity> cityEntity = cityRepository.findByName(name);
        log.info("City found: {}", cityEntity.isPresent());
        return cityEntity.map(cityEntityMapper::toModel);
    }
}