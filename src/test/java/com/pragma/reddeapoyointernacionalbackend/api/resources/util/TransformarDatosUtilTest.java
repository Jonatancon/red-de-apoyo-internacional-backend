package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformarDatosUtilTest {

    @InjectMocks
    TransformarDatosUtil tranform;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listaCasasDto() {
        assertNotNull(tranform.listaCasasDto(listaCasa()));
        assertEquals("Medellin", tranform.listaCasasDto(listaCasa()).get(0)
                .getUsuarioDto().getCiudad());
    }

    private List<CasaEntity> listaCasa () {
        List<CasaEntity> addCasa = new ArrayList<>();

        UsuarioEntity user = UsuarioEntity.builder().nombreCompleto("jhon")
                .ciudad("Medellin").pais("Colombia").build();

        addCasa.add(CasaEntity.builder().idCasa(1).pais("colombia").ciudad("Medellin")
                .direccion("2020").telefono("123").estado("Antioquia").usuarioEntity(user).build()
        );

        return addCasa;
    }

}