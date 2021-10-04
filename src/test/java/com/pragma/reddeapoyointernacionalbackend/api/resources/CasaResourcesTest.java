package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.BusquedasUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.data.repository.UsuarioRepository;
import com.pragma.reddeapoyointernacionalbackend.domain.services.CasaService;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CasaResourcesTest {

    @Mock
    private BusquedasUtil busquedasUtil;

    @Mock
    private CasaService casaService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    CasaResources casaResources;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarNuevaCasaErrorCampos() {
        when(bindingResult.hasErrors()).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                casaResources.guardarNuevaCasa(crearCasaDto(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Campos Incorrectos");
    }

    @Test
    void guardarNuevaCasa() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(busquedasUtil.obtenerUsuarioEntityFromNombreUsuario("jrstark")).thenReturn(crearUsuarioMock().get());
        when(casaService.crearUnaCasa(any())).thenReturn(new CasaEntity());

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                casaResources.guardarNuevaCasa(crearCasaDto(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Casa Guardada");
    }

    @Test
    void newCasaErroInterno () {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(casaService.crearUnaCasa(any())).thenThrow(MockitoException.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                casaResources.guardarNuevaCasa(crearCasaDto(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Oooops... org.mockito.exceptions.base.MockitoException");
    }

    @Test
    void obtenerCasas() {
        when(casaService.todasLasCasas()).thenReturn(crearLista());

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<List<CasaDto>> responseEntity =
                casaResources.obtenerCasas();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertEquals("jrstark", responseEntity.getBody().get(0).getNombreUsuario());
    }

    private CasaDto crearCasaDto() {
        return CasaDto.builder().nombreUsuario("jrstark").foto(new byte[2]).pais("Colombia")
                .ciudad("Medellin").direccion("2020").telefono("123").build();
    }

    private Optional<UsuarioEntity> crearUsuarioMock () {
        Set<RolEntity> roles = new HashSet<>();
        roles.add(new RolEntity(1, NombreRol.ANFITRION));
        roles.add(new RolEntity(2, NombreRol.USUARIO));

        return Optional.of( UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark")
                .password("123").nombreCompleto("Jonatan Restrepo").pais("colombia")
                .ciudad("Medellin").rolEntity(roles).build() );
    }

    private List<CasaEntity> crearLista() {
        List<CasaEntity> casasAdd = new ArrayList<>();
        CasaEntity casa = CasaEntity.builder().usuarioEntity(crearUsuarioMock().get())
                .ciudad("Medellin").build();
        casasAdd.add(casa);
        return casasAdd;
    }
}