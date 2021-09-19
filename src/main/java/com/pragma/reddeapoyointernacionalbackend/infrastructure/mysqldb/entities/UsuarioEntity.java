package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("usuario")
public class UsuarioEntity {

    @Id
    @Column("id_usuario")
    private Integer idUsuario;
    @Column("nombre_usuario")
    private String NombreUsuario;
    @Column("password")
    private String password;
    @Column("nombre_completo")
    private String nombreCompleto;
    @Column("ciudad")
    private String ciudad;
    @Column("pais")
    private String pais;
    @Column("fk_rol")
    private RolEntity rolEntity;
}
