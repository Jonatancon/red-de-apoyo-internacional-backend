package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CalificacionCasaDto;
import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionCasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.BusquedaCasasService;
import com.pragma.reddeapoyointernacionalbackend.domain.services.CalificacionCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionCasaUtil {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CalificacionCasaService calificacionService;
    @Autowired
    private BusquedaCasasService busqueda;
    @Autowired
    private ManejoDatosUtil manejoDatosUtil;

    public void guardarCalificacion(CalificacionCasaDto calificacion, String token) {
        CasaEntity casa = obtener(calificacion.getIdCasa());
        String userName = jwtProvider.getNombreUsuarioFromToken(token);
        UsuarioEntity user = manejoDatosUtil.buscarUsuario(userName);
        CalificacionCasaEntity calificacionCasa = CalificacionCasaEntity.builder()
                .calificacionCasa(calificacion.getPuntajeCasa()).calificacionAnfitrion(calificacion.getPuntajeAnfitrion())
                .casa(casa).usuarioCalificador(user).comentario(calificacion.getComentario()).build();

        calificacionService.saveNewCalificacion(calificacionCasa);
    }

    public CasaEntity obtener(String id) {
        return busqueda.buscarCasa(Integer.parseInt(id)).orElseGet(null);
    }

    public List<CalificacionCasaDto> todasCalificaciones(String id) {
        return calificacionService.getAllCalificacionesByIdCasa(Integer.parseInt(id))
                .stream().map(calificacion -> CalificacionCasaDto.builder()
                        .idCasa(calificacion.getCasa().getIdCasa().toString())
                        .idUsuario(calificacion.getUsuarioCalificador().getNombreUsuario())
                        .comentario(calificacion.getComentario()).puntajeCasa(calificacion.getCalificacionCasa())
                        .puntajeAnfitrion(calificacion.getCalificacionAnfitrion()).build()
                ).collect(Collectors.toList());
    }

    public List<CalificacionCasaDto> todasCalificacionesByUserName(String id) {
        return calificacionService.getAllCalificacionesByUserName(id)
                .stream().map(calificacion -> CalificacionCasaDto.builder()
                        .idCasa(calificacion.getCasa().getIdCasa().toString())
                        .idUsuario(calificacion.getUsuarioCalificador().getNombreUsuario())
                        .comentario(calificacion.getComentario()).puntajeCasa(calificacion.getCalificacionCasa())
                        .puntajeAnfitrion(calificacion.getCalificacionAnfitrion()).build()
                ).collect(Collectors.toList());
    }
}
