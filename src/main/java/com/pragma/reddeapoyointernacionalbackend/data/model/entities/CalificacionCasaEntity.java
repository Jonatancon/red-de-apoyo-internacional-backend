package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CalificacionCasaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion_casa")
    private Integer idCalificacionCasa;

    @Column(length = 500)
    private String comentario;

    @Column(name = "calificacion_casa")
    private Integer calificacionCasa;

    @Column(name = "calificacion_anfitrion")
    private Integer calificacionAnfitrion;

    @JoinColumn(name = "fk_casa_afiliada")
    @ManyToOne
    private CasaEntity casa;

    public Integer getIdCalificacionCasa() {
        return idCalificacionCasa;
    }

    public String getComentario() {
        return comentario;
    }

    public Integer getCalificacionCasa() {
        return calificacionCasa;
    }

    public Integer getCalificacionAnfitrion() {
        return calificacionAnfitrion;
    }

    public CasaEntity getCasa() {
        return casa;
    }
}
