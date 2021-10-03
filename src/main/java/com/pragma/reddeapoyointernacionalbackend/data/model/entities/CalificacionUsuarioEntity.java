package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

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

    public String getComentario() {
        return comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

}
