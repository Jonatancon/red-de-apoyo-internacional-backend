package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public class CasaDto {

    private Integer idCasa;

    @NotNull
    @NotBlank
    private String direccion;

    @NotNull
    @NotBlank
    private String ciudad;

    @NotNull
    @NotBlank
    private String pais;

    @NotNull
    @NotBlank
    private String estado;

    @NotNull
    @NotBlank
    private String telefono;

    private byte[] foto;

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
}
