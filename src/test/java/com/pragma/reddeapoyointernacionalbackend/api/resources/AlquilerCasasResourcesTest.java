package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.AlquilerDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.TransformarDatosUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.DisponibilidadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlquilerCasasResourcesTest {

    @InjectMocks
    AlquilerCasasResources alquiler;

    @Mock
    TransformarDatosUtil transform;

    @Mock
    DisponibilidadService disponibilidad;

    @Mock
    BindingResult bindingResult;

    String token = "OK";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearNuevoAlquilerFalloCampos() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                alquiler.crearNuevoAlquiler(crearAlquiler(), bindingResult, token);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Campos Incompletos");
    }

    @Test
    void crearNuevoAlquilerExiste() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(transform.decodificadorClave(anyString())).thenReturn(1);
        when(disponibilidad.existeCasaAlquiladaEnFecha(anyInt(), anyString())).thenReturn(false);

        ResponseEntity<MessageDto> responseEntity =
                alquiler.crearNuevoAlquiler(crearAlquiler(), bindingResult, token);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("La Casa Ya esta Ocupada Para esa Fecha");
    }

    @Test
    void crearNuevoAlquiler() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(transform.decodificadorClave(anyString())).thenReturn(1);
        when(disponibilidad.existeCasaAlquiladaEnFecha(anyInt(), anyString())).thenReturn(true);
        when(transform.crearNuevaDisponiblidad(any(), any())).thenReturn(crearDisponibilidad());
        when(disponibilidad.crearNuevaReserva(any())).thenReturn(crearDisponibilidad());

        ResponseEntity<MessageDto> responseEntity =
                alquiler.crearNuevoAlquiler(crearAlquiler(), bindingResult, token);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Alquiler Guardado");
    }

    private AlquilerDto crearAlquiler() {
        return AlquilerDto.builder().fechaInicio("123").idCasa("1")
                .fechaFinal("321").build();
    }

    private DisponibilidadEntity crearDisponibilidad() {
        return DisponibilidadEntity.builder().idDisponibilidad(1).build();
    }
}