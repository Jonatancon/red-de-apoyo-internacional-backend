package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
public class DisponibilidadDto {

    @NotBlank
    @NotNull
    private String fechaInicial;
    @NotBlank
    @NotNull
    private String fechaFinal;
    @NotNull
    @NotBlank
    private String iDcasa;
    private String usuarioReserver;
    private boolean calificoUsuario;
    private boolean calificoAnfitrion;

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getiDcasa() {
        return iDcasa;
    }

    public String getUsuarioReserver() {
        return usuarioReserver;
    }

    public boolean isCalificoUsuario() {
        return calificoUsuario;
    }

    public boolean isCalificoAnfitrion() {
        return calificoAnfitrion;
    }
}
