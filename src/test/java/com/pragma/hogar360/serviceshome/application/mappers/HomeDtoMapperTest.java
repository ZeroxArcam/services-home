//package com.pragma.hogar360.serviceshome.application.mappers;
//
//import com.pragma.hogar360.serviceshome.application.dto.request.SaveHomeRequest;
//import com.pragma.hogar360.serviceshome.domain.model.HomeModel;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.time.LocalDate;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(MockitoExtension.class)
//public class HomeDtoMapperTest {
//
//    @Spy
//    private HomeDtoMapperImpl homeDtoMapper;
//
//    @Test
//    void testRequestToModel() {
//        // Crear un objeto SaveHomeRequest de ejemplo
//        SaveHomeRequest request = new SaveHomeRequest(
//                "Mi Casa",
//                "Una hermosa casa",
//                "Apartamento",
//                3,
//                2,
//                150000.0,
//                "Medellín",
//                "Antioquia",
//                LocalDate.now(),
//                "Activa",
//                "Tecnología"
//        );
//
//        // Usar el mapper para convertir el request a HomeModel
//        HomeModel homeModel = homeDtoMapper.requestToModel(request);
//
//        // Verificar que el mapeo fue exitoso
//        assertNotNull(homeModel);
//        assertNotNull(homeModel.getBasicInfo());
//        assertNotNull(homeModel.getPropertyDetails());
//        assertNotNull(homeModel.getPublicationInfo());
//
//        // Verificar los valores de las propiedades mapeadas
//        assertEquals(request.name(), homeModel.getBasicInfo().getName());
//        assertEquals(request.price(), homeModel.getBasicInfo().getPrice());
//        assertEquals(request.city(), homeModel.getBasicInfo().getLocation().getCity().getName());
//        assertEquals(request.department(), homeModel.getBasicInfo().getLocation().getDepartment().getName());
//        assertEquals(request.description(), homeModel.getPropertyDetails().getDescription());
//        assertEquals(request.category(), homeModel.getPropertyDetails().getCategory().getName());
//        assertEquals(request.numberOfRooms(), homeModel.getPropertyDetails().getNumberOfRooms());
//        assertEquals(request.numberOfBathrooms(), homeModel.getPropertyDetails().getNumberOfBathrooms());
//        assertEquals(request.activePublicationDate(), homeModel.getPublicationInfo().getActivePublicationDate());
//        assertEquals(request.publicationStatus(), homeModel.getPublicationInfo().getPublicationStatus());
//    }
//}