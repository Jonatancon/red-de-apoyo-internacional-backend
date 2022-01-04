package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionUsuarioRepository extends JpaRepository<CalificacionUsuarioEntity, Integer> {

    List<CalificacionUsuarioEntity> findAllByUsuarioEntity_NombreUsuario(String nombreUsuario);
}
