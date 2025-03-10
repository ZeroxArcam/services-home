package com.pragma.hogar360.serviceshome.commons.configurations.beans;

import com.pragma.hogar360.serviceshome.domain.ports.in.CategoryServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.CategoryPersistencePort;
import com.pragma.hogar360.serviceshome.domain.usecases.CategoryUseCase;
import com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining Spring beans.
 * Provides beans for the domain and infrastructure layers.
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    /**
     * Defines the CategoryServicePort bean.
     *
     * @return An instance of CategoryServicePort.
     */
    @Bean
    public CategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    /**
     * Defines the CategoryPersistencePort bean.
     *
     * @return An instance of CategoryPersistencePort.
     */
    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
}