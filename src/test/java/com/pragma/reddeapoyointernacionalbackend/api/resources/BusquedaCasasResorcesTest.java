package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.BusquedaCasasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BusquedaCasasResorcesTest {

    @InjectMocks
    BusquedaCasasResorces busqueda;

    @Mock
    BusquedaCasasService busquedaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(busquedaService.obtenerPorCiudad(anyString())).thenReturn(listaCasa());
        when(busquedaService.obtenerPorPais(anyString())).thenReturn(listaCasa());
        when(busquedaService.obtenerPorEstado(anyString())).thenReturn(listaCasa());
        when(busquedaService.obtenerPorFecha(anyString())).thenReturn(listaCasa());
        when(busquedaService.obtenerTodasLasCasas()).thenReturn(listaCasa());
    }

    @Test
    void buscarTodasLasCasas() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<List<CasaDto>> responseEntity = busqueda.buscarTodasLasCasas();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void bucarPorPais() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<List<CasaDto>> responseEntity = busqueda.bucarPorPais("Colombia");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void bucarPorCiudad() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<List<CasaDto>> responseEntity = busqueda.bucarPorCiudad("Ciudad");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void bucarPorEstado() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<List<CasaDto>> responseEntity = busqueda.bucarPorEstado("Estado");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void bucarPorFecha() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<List<CasaDto>> responseEntity = busqueda.bucarPorFecha("fecha");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertNotNull(responseEntity.getBody());
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