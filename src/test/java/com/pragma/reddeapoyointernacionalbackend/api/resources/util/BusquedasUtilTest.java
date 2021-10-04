package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BusquedasUtilTest {

    @Mock
    UsuarioService usuarioService;

    @InjectMocks
    BusquedasUtil busquedasUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerUsuarioEntityFromNombreUsuario() {
        when(usuarioService.getUsuarioFromNombreUsuario(anyString())).thenReturn(crearUsuarioMock());

        assertNotNull(busquedasUtil.obtenerUsuarioEntityFromNombreUsuario(anyString()));
    }

    private Optional<UsuarioEntity> crearUsuarioMock () {
        Set<RolEntity> roles = new HashSet<>();
        roles.add(new RolEntity(1, NombreRol.ANFITRION));
        roles.add(new RolEntity(2, NombreRol.USUARIO));

        return Optional.of( UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark")
                .password("123").nombreCompleto("Jonatan Restrepo").pais("colombia")
                .ciudad("Medellin").rolEntity(roles).build() );
    }
}