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
    RolRepositoy rolRepositoy;

    @InjectMocks
    RolService rolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByNombreRol() {
        when(rolRepositoy.findByNombreRol(any())).thenReturn(crearRolEntity());

        assertNotNull(rolService.getByNombreRol(any()));
        assertEquals(crearRolEntity().get().getNombreRol(),
                rolService.getByNombreRol(any()).get().getNombreRol());
    }


    private Optional<RolEntity> crearRolEntity () {
        return Optional.of(RolEntity.builder().idRol(1).nombreRol(NombreRol.USUARIO).build());
    }

    @Test
    void crearRol() {
        when(rolRepositoy.save(any())).thenReturn(crearRolEntity().get());

        assertNotNull(rolService.crearRol(any()));

        assertEquals(crearRolEntity().get().getNombreRol(),
                rolService.crearRol(any()).getNombreRol());
    }
}