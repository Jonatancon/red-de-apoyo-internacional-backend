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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BusquedaCasasServiceTest {

    @Mock
    CasaRepository casaRepository;

    @InjectMocks
    BusquedaCasasService busquedaCasasService;

    @Mock
    DisponibilidadService disponibilidadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(casaRepository.findAllByCiudad(anyString())).thenReturn(listaCasa());
        when(casaRepository.findAllByPais(anyString())).thenReturn(listaCasa());
        when(casaRepository.findAllByEstado(anyString())).thenReturn(listaCasa());
        when(casaRepository.findAll()).thenReturn(listaCasa());
        when(disponibilidadService.obtenerCasaOcupadasPorFecha(anyString())).thenReturn(listaCasa());
    }

    @Test
    void obtenerTodasLasCasas() {
        assertNotNull(busquedaCasasService.obtenerPorCiudad(anyString()));
        assertNotNull(busquedaCasasService.obtenerPorPais(anyString()));
        assertNotNull(busquedaCasasService.obtenerPorEstado(anyString()));
        assertNotNull(busquedaCasasService.obtenerTodasLasCasas());

        assertTrue(busquedaCasasService.obtenerPorFecha("12/11/2021").isEmpty());

    }

    private List<CasaEntity> listaCasa () {
        List<CasaEntity> addList = new ArrayList<>();
        addList.add(CasaEntity.builder().idCasa(1).build());
        addList.add(CasaEntity.builder().idCasa(2).build());
        return addList;
    }
}