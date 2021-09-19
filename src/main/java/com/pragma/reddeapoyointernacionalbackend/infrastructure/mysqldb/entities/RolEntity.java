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
@Table("rol")
public class RolEntity {

    @Id
    @Column("id_rol")
    private Integer idRol;
    @Column("nombre_rol")
    private String nombreRol;
}
