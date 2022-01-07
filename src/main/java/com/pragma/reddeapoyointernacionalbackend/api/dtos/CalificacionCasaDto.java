package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
public class CalificacionCasaDto {

    @NotBlank
    @NotNull
    private String comentario;
    private Integer puntajeCasa;
    private Integer puntajeAnfitrion;
    @NotBlank
    @NotNull
    private String idCasa;
    private String idUsuario;

    public String getComentario() {
        return comentario;
    }

    public Integer getPuntajeCasa() {
        return puntajeCasa;
    }

    public Integer getPuntajeAnfitrion() {
        return puntajeAnfitrion;
    }

    public String getIdCasa() {
        return idCasa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
}
