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
@Table("calificacion_usuario")
public class CalificacionUsuarioEntity {

    @Id
    @Column("id_calificacion_usuario")
    private Integer idCalificacionUsuario;
    @Column("fk_usuario")
    private UsuarioEntity usuarioEntity;
    @Column("comentario")
    private String comentario;
    @Column("calificacion")
    private Integer calificacion;
}
