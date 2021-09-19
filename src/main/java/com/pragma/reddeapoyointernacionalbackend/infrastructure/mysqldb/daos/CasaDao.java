package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.CasaEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface CasaDao extends ReactiveSortingRepository<CasaEntity, Integer> {
}
