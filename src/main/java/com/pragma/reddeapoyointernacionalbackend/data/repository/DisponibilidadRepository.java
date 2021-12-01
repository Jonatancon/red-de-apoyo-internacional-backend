package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadEntity, Integer> {
}
