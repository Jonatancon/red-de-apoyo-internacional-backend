package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.data.repository.RolRepositoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RolServiceTest {

    @Mock
    private RolRepositoy rolRepositoy;

    @InjectMocks
    private RolService rolService;

    private final RolEntity rol = RolEntity.builder().idRol(1).nombreRol(NombreRol.ANFITRION).build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByNombreRol() {
        when(rolRepositoy.findByNombreRol(any(NombreRol.class))).thenReturn(Optional.of(rol));

        assertNotNull(rolService.getByNombreRol(NombreRol.ANFITRION));
    }

    @Test
    void crearRol() {
        when(rolRepositoy.save(any(RolEntity.class))).thenReturn(rol);

        assertNotNull(rolService.crearRol(rol));
    }
}