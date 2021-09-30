package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.data.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario() {
        when(usuarioRepository.save(any())).thenReturn(crearUsuarioMock().get());

        assertEquals("123", usuarioService.crearUsuario(any()).getPassword());
    }

    @Test
    void existeNombeUsuario() {
        when(usuarioRepository.existsByNombreUsuario(any())).thenReturn(true);

        assertEquals(true, usuarioService.existeNombeUsuario(any()));
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