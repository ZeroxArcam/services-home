package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.ports.in.HomeServicePort;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;

public class HomeUseCase implements HomeServicePort {
    private final HomePersistencePort homePersistencePort;

    public HomeUseCase(HomePersistencePort homePersistencePort) {
        this.homePersistencePort = homePersistencePort;
    }

    @Override
    public HomeModel createHome(HomeModel home){
        System.out.println(home.getBasicInfo().getLocation().getCityName());
       return homePersistencePort.saveHome(home);
    }


}
