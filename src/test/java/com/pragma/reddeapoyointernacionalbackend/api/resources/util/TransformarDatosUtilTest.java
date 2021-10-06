package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TransformarDatosUtilTest {

    @InjectMocks
    TransformarDatosUtil tranform;

    @Mock
    CodificadorUtil codificador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listaCasasDto() {
        assertNotNull(tranform.listaCasasDto(listaCasa()));
        assertEquals("Medellin", tranform.listaCasasDto(listaCasa()).get(0)
                .getUsuarioDto().getCiudad());
        assertEquals("OMeqgemheR9X2XPJSRKQUg==", tranform.listaCasasDto(listaCasa()).get(0)
                .getIdCasa());
    }

    @Test
    void listaCasaDtoError () throws Exception {
        when(codificador.encript(anyString())).thenThrow(MockitoException.class);
        List<CasaEntity> addCasa = new ArrayList<>();

        UsuarioEntity user = UsuarioEntity.builder().nombreCompleto("jhon")
                .ciudad("Medellin").pais("Colombia").build();

        addCasa.add(CasaEntity.builder().idCasa(null).pais("colombia").ciudad("Medellin")
                .direccion("2020").telefono("123").estado("Antioquia").usuarioEntity(user).build()
        );

        assertEquals("Error....Clave", tranform.listaCasasDto(listaCasa()).get(0).getIdCasa() );
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