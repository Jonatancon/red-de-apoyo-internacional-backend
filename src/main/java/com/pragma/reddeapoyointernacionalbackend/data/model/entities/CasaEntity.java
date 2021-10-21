package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CasaEntity {

    @Id
    @Column(name = "id_casa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCasa;

    private String direccion;

    private String estado;

    private String ciudad;

    private String pais;

    private String telefono;

    @Lob
    private byte[] foto;

    @JoinColumn(name = "fk_propietario")
    @ManyToOne
    private UsuarioEntity usuarioEntity;

    public Integer getIdCasa() {
        return idCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getEstado() {
        return estado;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }
}
