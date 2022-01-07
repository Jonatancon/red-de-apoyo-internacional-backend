package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionCasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionCasaRepository extends JpaRepository<CalificacionCasaEntity, Integer> {

    List<CalificacionCasaEntity> findAllByCasa_IdCasa(Integer id);
    List<CalificacionCasaEntity> findAllByCasa_UsuarioEntity_NombreUsuario(String nombreUsuario);
}
