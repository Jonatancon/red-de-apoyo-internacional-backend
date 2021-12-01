package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    @NotBlank
    @NotNull
    private String nombreUsuario;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String nombres;

    @NotBlank
    @NotNull
    private String apellidos;

    @NotBlank
    @NotNull
    private String ciudad;

    @NotBlank
    @NotNull
    private String pais;

    private Set<String> roles = new HashSet<>();

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {return apellidos;}

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
