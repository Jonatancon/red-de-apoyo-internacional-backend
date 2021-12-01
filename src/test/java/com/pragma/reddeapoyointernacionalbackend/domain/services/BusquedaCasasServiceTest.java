package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CasaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BusquedaCasasServiceTest {

    @InjectMocks
    private BusquedaCasasService busquedaCasasService;

    @Mock
    private CasaRepository casaRepository;

    private CasaEntity casa = crearCasa().get(0);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerPorPais() {
        when(casaRepository.findAllByPais(anyString())).thenReturn(crearCasa());
        assertNotNull(busquedaCasasService.obtenerPorPais("Colombia"));
    }

    @Test
    void obtenerPorCiudad() {
        when(casaRepository.findAllByCiudad(anyString())).thenReturn(crearCasa());
        assertNotNull(busquedaCasasService.obtenerPorCiudad("Medellin"));
    }

    @Test
    void obtenerPorEstado() {
        when(casaRepository.findAllByEstado(anyString())).thenReturn(crearCasa());
        assertNotNull(busquedaCasasService.obtenerPorEstado("Antioquia"));
    }

    @Test
    void obtenerTodasLasCasas() {
        when(casaRepository.findAll()).thenReturn(crearCasa());
        assertNotNull(busquedaCasasService.obtenerTodasLasCasas());
    }

    @Test
    void buscarCasa() {
        when(casaRepository.findById(anyInt())).thenReturn(Optional.of(casa));
        assertNotNull(busquedaCasasService.buscarCasa(1));
    }

    private List<CasaEntity> crearCasa() {
        List<CasaEntity> listaCasas = new ArrayList<>();

        listaCasas.add(CasaEntity.builder().idCasa(1).usuarioEntity(null).ciudad("Medellin")
                .direccion("AAA").estado("AAA").pais("Colombia").build());

        return listaCasas;
    }
}