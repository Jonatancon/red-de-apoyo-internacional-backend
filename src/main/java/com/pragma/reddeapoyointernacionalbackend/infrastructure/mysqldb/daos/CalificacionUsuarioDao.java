package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.CalificacionUsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface CalificacionUsuarioDao extends ReactiveSortingRepository<CalificacionUsuarioEntity, Integer> {
}
