package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class DisponibilidadEntity {

    @Id
    @Column(name = "id_disponibilidad")
    private Integer idDisponibilidad;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_llegada")
    private String fechaLlegada;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_salida")
    private String fechaSalida;

    @JoinColumn(name = "fk_casa")
    @ManyToOne
    private CasaEntity casaEntity;
}
