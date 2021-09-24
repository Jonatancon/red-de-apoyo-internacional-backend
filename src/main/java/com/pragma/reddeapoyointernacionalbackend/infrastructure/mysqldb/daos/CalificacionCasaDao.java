package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.CalificacionCasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionCasaDao extends JpaRepository<CalificacionCasaEntity, Integer> {
}
