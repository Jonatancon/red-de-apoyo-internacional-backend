package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.DisponibilidadEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface DisponibilidadDao extends ReactiveSortingRepository<DisponibilidadEntity, Integer> {
}
