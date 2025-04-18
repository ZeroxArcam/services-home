package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import com.pragma.hogar360.serviceshome.factory.DepartmentModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentUseCaseTest {

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private DepartmentUseCase departmentUseCase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void save_shouldSaveDepartment_whenDepartmentIsValid() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        when(departmentPersistencePort.existsByName(departmentModel.getName())).thenReturn(false);

        // Act
        departmentUseCase.save(departmentModel);

        // Assert
        verify(departmentPersistencePort, times(1)).save(departmentModel);
    }

    @Test
    void save_shouldThrowDuplicateDepartmentNameException_whenDepartmentAlreadyExists() {
        // Arrange
        DepartmentModel departmentModel = DepartmentModelFactory.createDefaultDepartmentModel();
        when(departmentPersistencePort.existsByName(departmentModel.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateDepartmentNameException.class, () -> departmentUseCase.save(departmentModel));
        verify(departmentPersistencePort, never()).save(departmentModel);
    }
}