package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionCasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CalificacionCasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalificacionCasaService {

    @Autowired
    private CalificacionCasaRepository calificacion;


    public List<CalificacionCasaEntity> getAllCalificacionesByIdCasa(Integer id) {
        return calificacion.findAllByCasa_IdCasa(id);
    }

    public List<CalificacionCasaEntity> getAllCalificacionesByUserName(String userName) {
        return calificacion.findAllByCasa_UsuarioEntity_NombreUsuario(userName);
    }

    public void saveNewCalificacion(CalificacionCasaEntity newCalificacion){
        calificacion.save(newCalificacion);
    }
}
