package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface UsuarioDao extends ReactiveSortingRepository<UsuarioEntity, Integer> {
}
