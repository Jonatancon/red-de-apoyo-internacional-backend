package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;


import com.pragma.reddeapoyointernacionalbackend.domain.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolDao extends JpaRepository<RolEntity, Integer> {

    Optional<RolEntity> findByNombreRol (NombreRol nombreRol);
}
