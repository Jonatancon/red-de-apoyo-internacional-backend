package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.CalificacionCasaEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface CalificacionCasaDao extends ReactiveSortingRepository<CalificacionCasaEntity, Integer> {
}
