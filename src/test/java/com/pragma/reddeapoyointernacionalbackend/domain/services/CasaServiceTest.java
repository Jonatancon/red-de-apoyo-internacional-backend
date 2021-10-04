package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CasaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CasaServiceTest {

    @Mock
    private CasaRepository casaRepository;

    @InjectMocks
    private CasaService casaService;

    CasaEntity casa = new CasaEntity();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        casa = crearCasa();
    }

    @Test
    void crearUnaCasa() {
        when(casaRepository.save(any())).thenReturn(casa);

        assertNotNull(casaService.crearUnaCasa(any()).getPais());
    }

    @Test
    void todasLasCasas() {
        List<CasaEntity> addCasa = new ArrayList<>();
        addCasa.add(casa);

        when(casaRepository.findAll()).thenReturn(addCasa);

        assertNotNull(casaService.todasLasCasas());
    }

    private CasaEntity crearCasa() {
        return CasaEntity.builder().idCasa(1).ciudad("Medellin")
                .pais("Colombia").direccion("2020").telefono("123")
                .usuarioEntity(null).foto(null).build();
    }
}