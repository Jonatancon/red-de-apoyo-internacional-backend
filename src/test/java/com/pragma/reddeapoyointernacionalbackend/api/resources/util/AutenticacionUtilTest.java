package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.services.RolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AutenticacionUtilTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtProvider jwtProvider;

    @Mock
    RolService rolService;

    @InjectMocks
    AutenticacionUtil autenticacionUtil;

    UsuarioDto usuarioDto;
    LoginUsuarioDto loginUsuarioDto = new LoginUsuarioDto("jrstark", "1234");
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqcnN0YXJrIiwiaWF0IjoxNjMyOTQ5NzA1LCJyb2xlcyI6WyJVU1VBUklPIl0sImV4cCI6MTYzMjk1MDYwNX0.sHyjaILxCGln8KWZS077ahxQu8lVVkS9U1JkAexj_m39zqC-s0rvQQ5FpPSeJLm4FD8O6_Mxtbl6GJgI7ZppDw";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearUsuario() {
        when(rolService.getByNombreRol(NombreRol.USUARIO))
                .thenReturn(Optional.of(new RolEntity(1, NombreRol.USUARIO)));
        when(rolService.getByNombreRol(NombreRol.ANFITRION))
                .thenReturn(Optional.of(new RolEntity(2, NombreRol.ANFITRION)));
        when(passwordEncoder.encode(any())).thenReturn("password");

        assertNotNull(autenticacionUtil.crearUsuario(crearUsuarioDto()));
    }

    @Test
    void autenticarUsuario() {
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(
                loginUsuarioDto.getNombreUsuario(), loginUsuarioDto.getPassword()));
        when(jwtProvider.generarToken(any())).thenReturn(token);

        assertNotNull(autenticacionUtil.autenticarUsuario(loginUsuarioDto));
    }

    @Test
    void refreshToken() throws ParseException {
        when(jwtProvider.refreshToken(any())).thenReturn(token);

        assertNotNull(autenticacionUtil.refreshToken(any()));
    }

    private UsuarioDto crearUsuarioDto() {
        Set<String> roles = new HashSet<>();
        roles.add("anfitrion");
        usuarioDto = new UsuarioDto();
        usuarioDto.setNombreUsuario("jrstark");
        usuarioDto.setNombreCompleto("Jonatan Restrepo");
        usuarioDto.setPais("Colombia");
        usuarioDto.setCiudad("Medellin");
        usuarioDto.setPassword("123");
        usuarioDto.setRoles(roles);

        return usuarioDto;
    }
}