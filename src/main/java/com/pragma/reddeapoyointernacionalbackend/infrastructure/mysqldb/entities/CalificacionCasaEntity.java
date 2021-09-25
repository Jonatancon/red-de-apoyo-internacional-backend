package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

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

    public void setIdCalificacionCasa(Integer idCalificacionCasa) {
        this.idCalificacionCasa = idCalificacionCasa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacionCasa() {
        return calificacionCasa;
    }

    public void setCalificacionCasa(Integer calificacionCasa) {
        this.calificacionCasa = calificacionCasa;
    }

    public Integer getCalificacionAnfitrion() {
        return calificacionAnfitrion;
    }

    public void setCalificacionAnfitrion(Integer calificacionAnfitrion) {
        this.calificacionAnfitrion = calificacionAnfitrion;
    }

    public CasaEntity getCasa() {
        return casa;
    }

    public void setCasa(CasaEntity casa) {
        this.casa = casa;
    }
}
