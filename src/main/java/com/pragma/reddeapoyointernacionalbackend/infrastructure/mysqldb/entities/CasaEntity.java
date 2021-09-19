package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("casa")
public class CasaEntity {

    @Id
    @Column("id_casa")
    private Integer idCasa;
    @Column("direccion")
    private String direccion;
    @Column("ciudad")
    private String ciudad;
    @Column("pais")
    private String pais;
    @Column("telefono")
    private String telefono;
    @Column("foto")
    private byte[] foto;
    @Column("fk_propietario")
    private UsuarioEntity usuarioEntity;
}
