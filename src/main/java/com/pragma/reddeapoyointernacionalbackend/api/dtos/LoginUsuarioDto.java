package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUsuarioDto {

    @NotBlank
    @NotNull
    private String nombreUsuario;

    @NotBlank
    @NotNull
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

}
