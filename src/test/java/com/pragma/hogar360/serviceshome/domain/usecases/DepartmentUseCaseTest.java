package com.pragma.hogar360.serviceshome.domain.usecases;

import com.pragma.hogar360.serviceshome.domain.exceptions.DuplicateDepartmentNameException;
import com.pragma.hogar360.serviceshome.domain.model.DepartmentModel;
import com.pragma.hogar360.serviceshome.domain.ports.out.DepartmentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentUseCaseTest {

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private DepartmentUseCase departmentUseCase;

    private DepartmentModel departmentModel;

    @BeforeEach
    void setUp() {
        departmentModel = new DepartmentModel(1L, "Test Department", "Test Description");
    }

    @Test
    void createDepartment_shouldCreateDepartment_whenNameIsUnique() {
        when(departmentPersistencePort.existsByName("Test Department")).thenReturn(false);
        when(departmentPersistencePort.saveDepartment(departmentModel)).thenReturn(departmentModel);

        DepartmentModel result = departmentUseCase.createDepartment(departmentModel);

        assertEquals(departmentModel, result);
        verify(departmentPersistencePort).existsByName("Test Department");
        verify(departmentPersistencePort).saveDepartment(departmentModel);
    }

    @Test
    void createDepartment_shouldThrowDuplicateDepartmentNameException_whenNameExists() {
        when(departmentPersistencePort.existsByName("Test Department")).thenReturn(true);

        assertThrows(DuplicateDepartmentNameException.class, () -> departmentUseCase.createDepartment(departmentModel));
        verify(departmentPersistencePort).existsByName("Test Department");
        verify(departmentPersistencePort, never()).saveDepartment(any());
    }

    @Test
    void getDepartmentByName_shouldReturnDepartment_whenDepartmentExists() {
        when(departmentPersistencePort.findByName("Test Department")).thenReturn(Optional.of(departmentModel));

        Optional<DepartmentModel> result = departmentUseCase.getDepartmentByName("Test Department");

        assertTrue(result.isPresent());
        assertEquals(departmentModel, result.get());
        verify(departmentPersistencePort).findByName("Test Department");
    }

    @Test
    void getDepartmentByName_shouldReturnEmptyOptional_whenDepartmentDoesNotExist() {
        when(departmentPersistencePort.findByName("Nonexistent Department")).thenReturn(Optional.empty());

        Optional<DepartmentModel> result = departmentUseCase.getDepartmentByName("Nonexistent Department");

        assertFalse(result.isPresent());
        verify(departmentPersistencePort).findByName("Nonexistent Department");
    }
}