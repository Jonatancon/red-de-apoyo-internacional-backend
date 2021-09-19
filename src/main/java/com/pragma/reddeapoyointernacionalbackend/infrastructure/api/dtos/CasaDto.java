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
public class CasaDto {

    @NotBlank
    @NotNull
    private String direccion;
    @NotBlank
    @NotNull
    private String ciudad;
    @NotBlank
    @NotNull
    private String pais;
    @NotBlank
    @NotNull
    private String telefono;
    @NotBlank
    @NotNull
    private byte[] foto;
    private UsuarioDto usuarioDto;
}
