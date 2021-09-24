package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.CasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasaDao  extends JpaRepository<CasaEntity, Integer> {
}
