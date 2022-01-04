package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionUsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CalificacionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalificacionUsuarioServices {

    @Autowired
    private CalificacionUsuarioRepository calificacion;

    public void saveCalificacion(CalificacionUsuarioEntity calificacionUser) {
        calificacion.save(calificacionUser);
    }

    public List<CalificacionUsuarioEntity> getAllCalificaciones(String nombreUsuario) {
        return calificacion.findAllByUsuarioEntity_NombreUsuario(nombreUsuario);
    }
}
