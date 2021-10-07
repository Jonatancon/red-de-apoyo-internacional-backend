package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public class AlquilerDto {

    @NotBlank
    @NotNull
    private String fechaInicio;

    @NotBlank
    @NotNull
    private String fechaFinal;

    private String idCasa;

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getIdCasa() {
        return idCasa;
    }
}
