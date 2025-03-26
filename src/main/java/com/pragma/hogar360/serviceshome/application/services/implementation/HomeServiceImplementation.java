package com.pragma.hogar360.serviceshome.application.services.implementation;

import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
import com.pragma.hogar360.serviceshome.application.dto.response.SaveHomeResponse;
import com.pragma.hogar360.serviceshome.application.mappers.HomeDtoMapper;
import com.pragma.hogar360.serviceshome.application.services.HomeService;
import com.pragma.hogar360.serviceshome.commons.configurations.utils.Constants;
import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HomeServiceImplementation implements HomeService {
    private final HomeDtoMapper homeDtoMapper;
    private final HomeServicePort homeServicePort;

//    public HomeServiceImplementation( HomeDtoMapper homeDtoMapper, HomeServicePort homeServicePort ) {
//        this.homeDtoMapper = homeDtoMapper;
//        this.homeServicePort = homeServicePort;
//    }

    @Override
    public SaveHomeResponse save(SaveHomeRequest request){
        homeServicePort.save(homeDtoMapper.requestToModel(request));
        return new SaveHomeResponse(Constants.SAVE_HOME_RESPONSE_MESSAGE, LocalDateTime.now());
    }

//    @Override
//    public void save(SaveHomeRequest request){
//        homeServicePort.save(homeDtoMapper.requestToModel(request));
//        //SaveHomeResponse(Constants.SAVE_HOME_RESPONSE_MESSAGE, LocalDateTime.now());
//    }

}
