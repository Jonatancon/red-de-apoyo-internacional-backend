package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CalificacionUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionUsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.CalificacionUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionUserUtil {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CalificacionUsuarioServices calificacionUsuarioServices;
    @Autowired
    private ManejoDatosUtil manejoDatosUtil;

    public void save(CalificacionUsuarioDto calificacion, String token) {
        String userName = jwtProvider.getNombreUsuarioFromToken(token);
        UsuarioEntity usuarioCalificado = manejoDatosUtil.buscarUsuario(calificacion.getUsuarioCalificado());
        UsuarioEntity anfitrionCalificador = manejoDatosUtil.buscarUsuario(userName);

        CalificacionUsuarioEntity calificacionUsuario = CalificacionUsuarioEntity.builder()
                .usuarioEntity(usuarioCalificado).anfitrionCalificador(anfitrionCalificador)
                .comentario(calificacion.getComentario()).calificacion(calificacion.getCalificacion())
                .build();

        calificacionUsuarioServices.saveCalificacion(calificacionUsuario);
    }

    public List<CalificacionUsuarioDto> obtenerCalificaciones(String id) {
        return calificacionUsuarioServices.getAllCalificaciones(id)
                .stream().map(calificacion -> CalificacionUsuarioDto.builder()
                        .comentario(calificacion.getComentario())
                        .usuarioCalificado(calificacion.getUsuarioEntity().getNombreUsuario())
                        .calificacion(calificacion.getCalificacion())
                        .anfitrionCalificador(calificacion.getAnfitrionCalificador().getNombreUsuario())
                        .build()).collect(Collectors.toList());
    }
}
