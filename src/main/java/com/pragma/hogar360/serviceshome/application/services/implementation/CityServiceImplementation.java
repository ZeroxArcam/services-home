package com.pragma.hogar360.serviceshome.application.services.implementation;
import com.pragma.hogar360.serviceshome.application.dto.request.SaveCityRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveCityResponse;
import com.pragma.hogar360.serviceshome.application.mappers.CityDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.CityService;
import com.pragma.hogar360.serviceshome.domain.ports.in.CityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CityServiceImplementation implements CityService {

    private final CityServicePort cityServicePort;
    private final CityDtoMapper cityDtoMapper;

    @Override
    public SaveCityResponse createCity(SaveCityRequest request){
        cityServicePort.save(cityDtoMapper.requestToModel(request));
        return new SaveCityResponse("ok", LocalDateTime.now());
    }
}