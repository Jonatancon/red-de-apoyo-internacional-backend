package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusquedasUtil {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;

    public UsuarioEntity obtenerUsuarioEntityFromNombreUsuario (String token) {
        String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);

        Optional<UsuarioEntity> user = usuarioService.getUsuarioFromNombreUsuario(nombreUsuario);

        return user.orElse(null);
    }
}
