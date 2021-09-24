package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;

import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.DisponibilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisponibilidadDao extends JpaRepository<DisponibilidadEntity, Integer> {
}
