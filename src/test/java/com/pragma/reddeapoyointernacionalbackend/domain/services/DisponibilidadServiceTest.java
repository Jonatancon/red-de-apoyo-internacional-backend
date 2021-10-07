package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.DisponibilidadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class DisponibilidadServiceTest {

    @Mock
    DisponibilidadRepository disponibilidadRepository;

    @InjectMocks
    DisponibilidadService disponibilidadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerCasaOcupadasPorFecha() {
        when(disponibilidadRepository.findAll()).thenReturn(listaDeCasa(3));
        assertNotNull(disponibilidadService.obtenerCasaOcupadasPorFecha("11/12/2021"));
    }

    @Test
    void existeCasaAlquiladaEnFechaDisponible() {
        when(disponibilidadRepository.findAllByCasaEntity_IdCasa(anyInt())).thenReturn(listaDeCasa(3));

        assertTrue(disponibilidadService.existeCasaAlquiladaEnFecha(1,"01/12/2020"));
    }

    @Test
    void existeCasaAlquiladaOcupada(){
        when(disponibilidadRepository.findAllByCasaEntity_IdCasa(anyInt())).thenReturn(listaDeCasa(3));

        assertFalse(disponibilidadService.existeCasaAlquiladaEnFecha(1,"11/12/2020"));
    }

    @Test
    void crearUnaReserva() {
        when(disponibilidadRepository.save(any())).thenReturn(listaDeCasa(1).get(0));

        assertNotNull(disponibilidadService.crearNuevaReserva(any(DisponibilidadEntity.class)));
    }

    private CasaEntity crearCasaEntity(int numeroCasa) {
        return CasaEntity.builder().pais("Colombia").idCasa(numeroCasa).build();
    }

    private List<DisponibilidadEntity> listaDeCasa(int casas) {
        List<DisponibilidadEntity> addCasa = new ArrayList<>();

        for (int i = 0; i < casas; i++) {
            addCasa.add(DisponibilidadEntity.builder().idDisponibilidad(i)
                    .usuarioEntity(null).casaEntity(crearCasaEntity(i))
                    .fechaLlegada("11/12/202"+i).fechaSalida("11/12/202"+i).build()
            );
        }
        return addCasa;
    }
}