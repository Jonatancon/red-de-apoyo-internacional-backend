package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CalificacionUsuarioEntity {

    @Id
    @Column(name = "id_calificacion_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacionUsuario;

    @Column(length = 500)
    private String comentario;

    private Integer calificacion;

    @JoinColumn(name = "fk_usuario")
    @ManyToOne
    private UsuarioEntity usuarioEntity;

    public Integer getIdCalificacionUsuario() {
        return idCalificacionUsuario;
    }

    public void setIdCalificacionUsuario(Integer idCalificacionUsuario) {
        this.idCalificacionUsuario = idCalificacionUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
}
