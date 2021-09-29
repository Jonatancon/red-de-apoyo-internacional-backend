package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CalificacionCasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionCasaRepository extends JpaRepository<CalificacionCasaEntity, Integer> {
}
