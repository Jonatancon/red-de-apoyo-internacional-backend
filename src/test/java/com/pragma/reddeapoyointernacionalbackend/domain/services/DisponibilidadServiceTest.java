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