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
public class UsuarioDto {

    @NotBlank
    @NotNull
    private String NombreUsuario;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @NotNull
    private String nombreCompleto;
    @NotBlank
    @NotNull
    private String ciudad;
    @NotBlank
    @NotNull
    private String pais;
    private RolDto rolDto;
}
