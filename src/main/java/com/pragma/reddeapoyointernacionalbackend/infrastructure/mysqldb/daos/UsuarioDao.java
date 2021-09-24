package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByNombreUsuario (String nombreUsuario);
    Boolean existsByNombreUsuario (String nombreUsuario);

}
