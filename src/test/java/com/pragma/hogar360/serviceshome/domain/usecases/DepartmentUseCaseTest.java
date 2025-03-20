package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.factory.DepartmentModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentUseCaseTest {

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private DepartmentUseCase departmentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDepartment_Success() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        when(departmentPersistencePort.existsByName(departmentModel.getName())).thenReturn(false);
        when(departmentPersistencePort.saveDepartment(departmentModel)).thenReturn(departmentModel);

        // Act
        DepartmentModel result = departmentUseCase.createDepartment(departmentModel);

        // Assert
        assertNotNull(result);
        assertEquals(departmentModel, result);
        verify(departmentPersistencePort, times(1)).existsByName(departmentModel.getName());
        verify(departmentPersistencePort, times(1)).saveDepartment(departmentModel);
    }

    @Test
    void testCreateDepartment_DuplicateName() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        when(departmentPersistencePort.existsByName(departmentModel.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateDepartmentNameException.class, () -> departmentUseCase.createDepartment(departmentModel));
        verify(departmentPersistencePort, times(1)).existsByName(departmentModel.getName());
        verify(departmentPersistencePort, never()).saveDepartment(departmentModel);
    }

    @Test
    void testGetDepartmentByName_Success() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        when(departmentPersistencePort.findByName(departmentModel.getName())).thenReturn(Optional.of(departmentModel));

        // Act
        Optional<DepartmentModel> result = departmentUseCase.getDepartmentByName(departmentModel.getName());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(departmentModel, result.get());
        verify(departmentPersistencePort, times(1)).findByName(departmentModel.getName());
    }

    @Test
    void testGetDepartmentByName_NotFound() {
        // Arrange
        String departmentName = "Nonexistent Department";
        when(departmentPersistencePort.findByName(departmentName)).thenReturn(Optional.empty());

        // Act
        Optional<DepartmentModel> result = departmentUseCase.getDepartmentByName(departmentName);

        // Assert
        assertFalse(result.isPresent());
        verify(departmentPersistencePort, times(1)).findByName(departmentName);
    }
}