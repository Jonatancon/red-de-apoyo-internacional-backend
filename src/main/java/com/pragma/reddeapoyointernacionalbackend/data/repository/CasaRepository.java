package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasaRepository extends JpaRepository<CasaEntity, Integer> {

    List<CasaEntity> findAllByPais (String pais);
    List<CasaEntity> findAllByCiudad (String ciudad);
    List<CasaEntity> findAllByEstado (String estado);

}
