package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AutenticacionUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RolService rolService;

    public  UsuarioEntity crearUsuario (UsuarioDto usuarioDto) {
        Set<RolEntity> roles = new HashSet<>();
        rolService.getByNombreRol(NombreRol.USUARIO).ifPresent(roles::add);

        if (usuarioDto.getRoles().contains("anfitrion"))
            rolService.getByNombreRol(NombreRol.ANFITRION).ifPresent(roles::add);

        return UsuarioEntity.builder().idUsuario(null).nombreUsuario(usuarioDto.getNombreUsuario())
                .password(passwordEncoder.encode(usuarioDto.getPassword()))
                .nombres(usuarioDto.getNombres()).apellidos(usuarioDto.getApellidos())
                .ciudad(usuarioDto.getCiudad()).pais(usuarioDto.getPais()).rolEntity(roles).build();
    }

    public JwtDto autenticarUsuario (LoginUsuarioDto loginUsuarioDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginUsuarioDto.getNombreUsuario(), loginUsuarioDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtDto(jwtProvider.generarToken(authentication));
    }

    public JwtDto refreshToken (JwtDto jwtDto) throws ParseException {
        return new JwtDto(jwtProvider.refreshToken(jwtDto));
    }
}
