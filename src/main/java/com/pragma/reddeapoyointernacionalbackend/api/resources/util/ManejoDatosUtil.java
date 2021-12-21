package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManejoDatosUtil {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;

    public CasaEntity crearCasa(CasaDto casaDto, String token) {

        String nameUser = jwtProvider.getNombreUsuarioFromToken(token);
        UsuarioEntity user = buscarUsuario(nameUser);

        return CasaEntity.builder().idCasa(null).direccion(casaDto.getDireccion()).pais(casaDto.getPais())
                .estado(casaDto.getEstado()).ciudad(casaDto.getCiudad()).telefono(casaDto.getTelefono())
                .urlFoto(casaDto.getUrlFoto()).usuarioEntity(user)
                .build();
    }

    public UsuarioEntity buscarUsuario (String nombreUsuario) {
        return usuarioService.getUsuarioFromNombreUsuario(nombreUsuario).orElse(null);
    }
}
