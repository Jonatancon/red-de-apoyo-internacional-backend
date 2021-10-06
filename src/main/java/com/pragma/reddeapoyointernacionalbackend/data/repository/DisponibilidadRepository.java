package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadEntity, Integer> {

}
