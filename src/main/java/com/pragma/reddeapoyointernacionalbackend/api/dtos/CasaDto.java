package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
public class CasaDto {

    private String idCasa;

    @NotBlank
    @NotNull
    private String direccion;

    @NotBlank
    @NotNull
    private String pais;

    @NotBlank
    @NotNull
    private String estado;

    @NotBlank
    @NotNull
    private String ciudad;

    @NotBlank
    @NotNull
    private String telefono;

    @NotBlank
    @NotNull
    private String urlFoto;

    public String getIdCasa() {
        return idCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUrlFoto() {
        return urlFoto;
    }
}
