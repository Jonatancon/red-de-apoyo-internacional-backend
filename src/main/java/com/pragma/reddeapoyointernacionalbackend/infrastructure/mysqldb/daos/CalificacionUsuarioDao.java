package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.CalificacionUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionUsuarioDao extends JpaRepository<CalificacionUsuarioEntity, Integer> {
}
