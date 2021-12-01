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
import static org.mockito.Mockito.when;

class DetalleUsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    DetalleUsuarioService detalleUsuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(usuarioRepository.findByNombreUsuario("jrstark")).thenReturn(crearUsuario());

        assertNotNull(detalleUsuarioService.loadUserByUsername("jrstark"));
    }

    private Optional<UsuarioEntity> crearUsuario () {
        Set<RolEntity> roles = new HashSet<>();
        roles.add(new RolEntity(1, NombreRol.ANFITRION));
        roles.add(new RolEntity(2, NombreRol.USUARIO));

        return Optional.of(UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark")
                .nombres("Jonatan Stiven").apellidos("Restrepo Lora").pais("colombia").ciudad("medellin")
                .password("1234").rolEntity(roles).build());
    }
}