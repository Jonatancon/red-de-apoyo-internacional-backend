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
@Table("calificacion_casa")
public class CalificacionCasaEntity {

    @Id
    @Column("id_calificacion_casa")
    private Integer idCalificacionCasa;
    @Column("fk_casa_afiliada")
    private CasaEntity casa;
    @Column("comentario")
    private String comentario;
    @Column("calificacion")
    private Integer calificacionCasa;
    @Column("calificacion_anfitrion")
    private Integer calificacionAnfitrion;
}
