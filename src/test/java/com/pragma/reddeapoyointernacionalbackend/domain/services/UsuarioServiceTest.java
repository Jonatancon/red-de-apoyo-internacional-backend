package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioEntity usuarioEntity = UsuarioEntity.builder().nombreUsuario("JRSTARK").build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario() {
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        assertNotNull(usuarioService.crearUsuario(usuarioEntity));
    }

    @Test
    void existeNombeUsuario() {
        when(usuarioRepository.existsByNombreUsuario(anyString())).thenReturn(true);

        assertTrue(usuarioService.existeNombeUsuario("JRSTARK"));
    }

    @Test
    void getUsuarioFromNombreUsuario() {
        when(usuarioRepository.findByNombreUsuario(anyString())).thenReturn(Optional.of(usuarioEntity));

        assertNotNull(usuarioService.getUsuarioFromNombreUsuario("JRSTARK"));
    }
}