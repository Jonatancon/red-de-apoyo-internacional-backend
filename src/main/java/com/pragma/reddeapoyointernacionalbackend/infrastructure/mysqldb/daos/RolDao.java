package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos;


import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDao extends JpaRepository<RolEntity, Integer> {
}
