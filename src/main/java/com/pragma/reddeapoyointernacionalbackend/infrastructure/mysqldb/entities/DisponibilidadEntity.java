package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("disponibilidad")
public class DisponibilidadEntity {

    @Id
    @Column("id_disponibilidad")
    private Integer idDisponibilidad;
    @Column("fecha_llegada")
    private String fechaLlegada;
    @Column("fecha_salida")
    private String fechaSalida;
    @Column("fk_casa")
    private CasaEntity casaEntity;
}
