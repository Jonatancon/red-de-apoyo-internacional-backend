package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.AutenticacionUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AuthenticationResourceTest {

    @InjectMocks
    private AuthenticationResource authenticationResource;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AutenticacionUtil autenticacionUtil;

    private LoginUsuarioDto loginUsuarioDto = LoginUsuarioDto.builder().nombreUsuario("jrstark").password("123").build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void newUserErrorCamposIncompletos() {
        when(bindingResult.hasErrors()).thenReturn(true);

        UsuarioDto user = createUsuarioDto();

        try{
            authenticationResource.newUser(user, bindingResult);
        }catch (RequestErrors e){
            assertEquals("Missing data", e.getMessage());
        }
    }

    @Test
    void newUserErrorNombreUsuarioExiste() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(usuarioService.existeNombeUsuario(anyString())).thenReturn(true);

        UsuarioDto user = createUsuarioDto();

        try{
            authenticationResource.newUser(user, bindingResult);
        }catch (RequestErrors e){
            assertEquals("This Name User Already Exists", e.getMessage());
        }
    }

    @Test
    void newUser() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(usuarioService.existeNombeUsuario(anyString())).thenReturn(false);
        when(autenticacionUtil.crearUsuario(any(UsuarioDto.class))).thenReturn(UsuarioEntity.builder().idUsuario(1).build());

        UsuarioDto user = createUsuarioDto();

        assertEquals("Data Save", authenticationResource.newUser(user, bindingResult).getBody().getMessage());
    }

    @Test
    void loginUsuarioErrorCamposIncompletos() {
        when(bindingResult.hasErrors()).thenReturn(true);

        try{
            authenticationResource.loginUsuario(loginUsuarioDto, bindingResult);
        }catch (RequestErrors e) {
            assertEquals("Missing data", e.getMessage());
        }
    }

    @Test
    void loginUsuario() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(autenticacionUtil.autenticarUsuario(any(LoginUsuarioDto.class)))
                .thenReturn(JwtDto.builder().token("token").build());

        assertEquals("token", authenticationResource.loginUsuario(
                loginUsuarioDto, bindingResult).getBody().getToken());
    }

    @Test
    void refreshToken() throws ParseException {
        when(autenticacionUtil.refreshToken(any(JwtDto.class))).thenReturn(JwtDto.builder().token("token").build());

        JwtDto jwtDto = JwtDto.builder().token("token").build();

        assertEquals("token", authenticationResource.refreshToken(jwtDto).getBody().getToken());
    }

    private UsuarioDto createUsuarioDto() {
        return UsuarioDto.builder().nombreUsuario("jrstark").password("123").nombres("Jonatan Stiven")
                .apellidos("Restrepo Lora").ciudad("Medellin").pais("Colombia").roles(null).build();
    }
}