package com.pragma.hogar360.serviceshome.domain.ports.out;

import com.pragma.hogar360.serviceshome.domain.model.HomeModel;

public interface HomePersistencePort {
//   HomeModel saveHome(HomeModel homeModel);
//   boolean existById(HomeModel homeModel);
   void save(HomeModel homeModel);
}
