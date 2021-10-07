package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.services.CasaService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BusquedasUtilTest {

    @Mock
    UsuarioService usuarioService;

    @Mock
    CasaService casaService;

    @Mock
    JwtProvider jwtProvider;

    @InjectMocks
    BusquedasUtil busquedasUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerUsuarioEntityFromNombreUsuario() {
        when(usuarioService.getUsuarioFromNombreUsuario(anyString())).thenReturn(crearUsuarioMock());
        when(jwtProvider.getNombreUsuarioFromToken(anyString())).thenReturn("jrstark");

        assertNotNull(busquedasUtil.obtenerUsuarioEntityFromNombreUsuario(anyString()));
    }

    @Test
    void buscarCasa() {
        when(casaService.buscarCasa(anyInt())).thenReturn(Optional.of(crearCasa()));

        assertNotNull(busquedasUtil.encontrarCasa(1));
    }

    private Optional<UsuarioEntity> crearUsuarioMock () {
        Set<RolEntity> roles = new HashSet<>();
        roles.add(new RolEntity(1, NombreRol.ANFITRION));
        roles.add(new RolEntity(2, NombreRol.USUARIO));

        return Optional.of( UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark")
                .password("123").nombreCompleto("Jonatan Restrepo").pais("colombia")
                .ciudad("Medellin").rolEntity(roles).build() );
    }

    private CasaEntity crearCasa() {
        return CasaEntity.builder().idCasa(1).ciudad("Medellin")
                .pais("Colombia").direccion("2020").telefono("123")
                .usuarioEntity(null).foto(null).build();
    }
}