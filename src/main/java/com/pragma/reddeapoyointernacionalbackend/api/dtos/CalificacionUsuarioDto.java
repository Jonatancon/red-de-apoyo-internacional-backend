package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
public class CalificacionUsuarioDto {

    @NotNull
    @NotBlank
    private String comentario;
    @NotNull
    @NotBlank
    private Integer calificacion;
    @NotNull
    @NotBlank
    private String usuarioCalificado;
    private String anfitrionCalificador;

    public String getComentario() {
        return comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public String getUsuarioCalificado() {
        return usuarioCalificado;
    }

    public String getAnfitrionCalificador() {
        return anfitrionCalificador;
    }
}
