package com.pragma.reddeapoyointernacionalbackend.infrastructure.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CalificacionUsuarioDto {

    private UsuarioDto usuarioDto;
    @NotBlank
    @NotNull
    private String comentario;
    @NotBlank
    @NotNull
    private Integer calificacion;
}
