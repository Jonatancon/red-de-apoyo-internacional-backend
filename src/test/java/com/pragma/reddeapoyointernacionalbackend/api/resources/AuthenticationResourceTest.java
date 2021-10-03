package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.AutenticacionUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationResourceTest {

    @InjectMocks
    AuthenticationResource authenticationResource;

    @Mock
    UsuarioService usuarioService;

    @Mock
    AutenticacionUtil autenticacionUtil;

    @Mock
    BindingResult bindingResult;



    @BeforeEach
    void setUp() {
    }

    @Test
    void newUserCamposMalPuestos()  {
        when(bindingResult.hasErrors()).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                authenticationResource.newUser(crearUsuarioDto(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Campos mal puestos");
    }

    @Test
    void newUserUsuarioExiste () {
        when(usuarioService.existeNombeUsuario(any())).thenReturn(Boolean.TRUE);
        when(bindingResult.hasErrors()).thenReturn(false);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                authenticationResource.newUser(crearUsuarioDto(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Ya se encuentra en uso");
    }

    @Test
    void newUserSinProblemas () {
        when(usuarioService.existeNombeUsuario(any())).thenReturn(Boolean.FALSE);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(usuarioService.crearUsuario(any())).thenReturn(crearUsuarioEntity());
        when(autenticacionUtil.crearUsuario(any())).thenReturn(crearUsuarioEntity());

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<MessageDto> responseEntity =
                authenticationResource.newUser(crearUsuarioDto(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Usuario Guardado");
    }

    @Test
    void loginUsuarioErrorLogin() {
        when(bindingResult.hasErrors()).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<JwtDto> responseEntity =
                authenticationResource.loginUsuario(crearLogin(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getToken()).isEqualTo("Campos Mal Puestos");
    }

    @Test
    void loginUsuario (){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(autenticacionUtil.autenticarUsuario(any()))
                .thenReturn(JwtDto.builder().token("OK").build());

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<JwtDto> responseEntity =
                authenticationResource.loginUsuario(crearLogin(), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getToken()).isEqualTo("OK");
    }

    @Test
    void refreshTokenError() throws ParseException {
        when(bindingResult.hasErrors()).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<JwtDto> responseEntity =
                authenticationResource.refreshToken(new JwtDto("OK"), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody().getToken()).isEqualTo("Sin Token");
    }

    @Test
    void refreshToken () throws ParseException {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(autenticacionUtil.refreshToken(any())).thenReturn(new JwtDto("OK"));

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<JwtDto> responseEntity =
                authenticationResource.refreshToken(new JwtDto("OK"), bindingResult);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getToken()).isEqualTo("OK");
    }

    private LoginUsuarioDto crearLogin () {

        return LoginUsuarioDto.builder().nombreUsuario("jrstark")
                .password("123").build();
    }

    private UsuarioDto crearUsuarioDto () {
        Set<String> rols = new HashSet<>();
        rols.add("usuario");

        return UsuarioDto.builder().nombreUsuario("jrstark").nombreCompleto("Jonatan Restrepo")
                .pais("Colombia").ciudad("Medellin").password("123").roles(rols).build();
    }

    private UsuarioEntity crearUsuarioEntity () {
        Set<RolEntity> rols = new HashSet<>();
        rols.add(new RolEntity(1, NombreRol.USUARIO));

        return UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark")
                .nombreCompleto("Jonatan Restrepo").pais("Colombia").ciudad("Medellin")
                .password("123").rolEntity(rols).build();
    }

}