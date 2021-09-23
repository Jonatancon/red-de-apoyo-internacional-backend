package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities;

import com.pragma.reddeapoyointernacionalbackend.domain.model.enums.NombreRol;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RolEntity {

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "nombre_rol")
    private NombreRol nombreRol;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public NombreRol getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(NombreRol nombreRol) {
        this.nombreRol = nombreRol;
    }
}
