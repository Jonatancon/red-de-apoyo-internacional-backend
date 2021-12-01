package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CasaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CasaServiceTest {

    @InjectMocks
    private CasaService casaService;

    @Mock
    private CasaRepository casaRepository;

    private CasaEntity casa = CasaEntity.builder().idCasa(1).ciudad("Medellin").pais("Colombia").build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUnaCasa() {
        when(casaRepository.save(any(CasaEntity.class))).thenReturn(casa);
        assertNotNull(casaService.crearUnaCasa(casa));
    }
}