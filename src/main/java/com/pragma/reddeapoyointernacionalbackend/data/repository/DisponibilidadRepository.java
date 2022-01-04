package com.pragma.reddeapoyointernacionalbackend.data.repository;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadEntity, Integer> {

    @Query(
            "SELECT d FROM DisponibilidadEntity d WHERE d.casaReservada.idCasa = ?1 AND " +
                    "(d.fechaLlegada BETWEEN ?2 AND ?3) AND (d.fechaSalida BETWEEN ?2 AND ?3)"
    )
    List<DisponibilidadEntity> findAllByCasaReservadaAndBetweenDate
            (Integer idCasa, LocalDate fechallegada, LocalDate fechaSalida);

    List<DisponibilidadEntity> findAllByCasaReservada_IdCasa(Integer idCasa);
}
