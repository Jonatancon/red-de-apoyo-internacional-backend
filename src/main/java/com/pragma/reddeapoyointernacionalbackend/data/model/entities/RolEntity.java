package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class RolEntity {

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "nombre_rol")
    @Enumerated(EnumType.STRING)
    private NombreRol nombreRol;

    public Integer getIdRol() {
        return idRol;
    }

    public NombreRol getNombreRol() {
        return nombreRol;
    }

}
