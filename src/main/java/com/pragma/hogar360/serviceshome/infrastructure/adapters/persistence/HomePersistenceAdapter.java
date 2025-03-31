package com.pragma.hogar360.serviceshome.infrastructure.adapters.persistence;
import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
import com.pragma.hogar360.serviceshome.domain.model.HomePublicationInfoModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.HomePersistencePort;
import com.pragma.hogar360.serviceshome.infrastructure.entities.HomeEntity;
import com.pragma.hogar360.serviceshome.infrastructure.mappers.HomeEntityMapper;
import com.pragma.hogar360.serviceshome.infrastructure.repositories.mysql.HomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
    @Override
    public boolean existsAddress(String address) {
        return homeRepository.existsByAddress(address);
    }

    @Override
    public List<HomeModel> findHomesToActivate(LocalDate date, HomePublicationInfoModel.PublicationStatus domainStatus) {
        HomeEntity.PublicationStatus entityStatus = homeEntityMapper.mapPublicationStatus(domainStatus);
        return homeRepository.findByActivePublicationDateAndPublicationStatus(date, entityStatus)
                .stream()
                .map(homeEntityMapper::toModel)
                .toList();
    }

    @Override
    public void saveAll(List<HomeModel> homes) {
        List<HomeEntity> entities = homes.stream()
                .map(homeEntityMapper::toEntity)
                .toList();

        homeRepository.saveAll(entities);
    }


//    @Scheduled(cron = "0 0 0 * * *")  // A medianoche todos los días
//    public void activateScheduledPublications(HomeModel homeModel) {
//        LocalDate today = LocalDate.now();
//
//        // 1. Buscar casas usando el puerto de persistencia (dominio puro)
//        List<HomeModel> homesToActivate = findHomesToActivate(
//                today,
//                HomePublicationInfoModel.PublicationStatus.PUBLICATION_PAUSED
//        );
//
//        if (!homesToActivate.isEmpty()) {
//            // 2. Actualizar estado en el modelo de dominio
//            homesToActivate.forEach(home ->
//                    home.getPublicationInfo().setPublicationStatus(
//                            HomePublicationInfoModel.PublicationStatus.PUBLISHED
//                    )
//            );
//
//            // 3. Guardar usando el puerto de persistencia
//            homeRepository.save(homeEntityMapper.toEntity(homesToActivate));
//
//            log.info("Se activaron {} casas programadas para publicación.", homesToActivate.size());
//        } else {
//            log.info("No hay casas programadas para publicación hoy.");
//        }
//    }


}