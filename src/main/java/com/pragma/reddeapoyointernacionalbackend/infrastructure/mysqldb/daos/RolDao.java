package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.RolEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface RolDao extends ReactiveSortingRepository<RolEntity, Integer> {
}
