package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.CityResponse;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCityResponse;
import com.pragma.hogar360.serviceshome.application.mappers.CityDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.CityService;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.model.CityModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.CityServicePort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation of the {@link CityService} interface, providing services for managing cities.
 * This class handles the creation and retrieval of city information.
 *
 * @author [Ciro Alfonso Pallares Fragozo]
 * @version 1.0
 * @since [16/3/2025]
 */
@Service
public class CityServiceImplementation implements CityService {

    /**
     * Port for domain-level city service operations.
     */
    private final CityServicePort cityServicePort;
    /**
     * Mapper for converting between city DTOs and domain models.
     */
    private final CityDtoMapper cityDtoMapper;
    /**
     * Constructor for CityServiceImplementation.
     *
     * @param cityServicePort Port for domain-level city service operations.
     * @param cityDtoMapper Mapper for converting between city DTOs and domain models.
     */
    public CityServiceImplementation(CityServicePort cityServicePort, CityDtoMapper cityDtoMapper) {
        this.cityServicePort = cityServicePort;
        this.cityDtoMapper = cityDtoMapper;
    }
    /**
     * Creates a new city based on the provided request.
     *
     * @param request The request containing the city details.
     * @return A {@link SaveCityResponse} indicating the success of the operation.
     */
    @Override
    public SaveCityResponse createCity(SaveCityRequest request) {
        CityModel cityModel = cityDtoMapper.requestToModel(request);
        CityModel savedCity = cityServicePort.createCity(cityModel);
        return new SaveCityResponse(Constants.SAVE_CITY_RESPONSE_MESSAGE, LocalDateTime.now());
    }
    /**
     * Retrieves a city by its name.
     *
     * @param name The name of the city to retrieve.
     * @return An {@link Optional} containing the {@link CityResponse} if found, or an empty {@link Optional} if not.
     */
    @Override
    public Optional<CityResponse> getCityByName(String name) {
        return cityServicePort.getCityByName(name)
                .map(cityDtoMapper::modelToResponse);
    }
}