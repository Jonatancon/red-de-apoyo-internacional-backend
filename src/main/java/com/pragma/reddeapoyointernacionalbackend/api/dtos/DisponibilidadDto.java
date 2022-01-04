package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class DisponibilidadDto {

    @NotBlank
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicial;
    @NotBlank
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFinal;
    @NotNull
    @NotBlank
    private String iDcasa;
    private String usuarioReserver;
    private boolean calificoUsuario;
    private boolean calificoAnfitrion;

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
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
