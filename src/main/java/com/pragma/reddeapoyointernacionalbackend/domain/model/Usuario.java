package com.pragma.reddeapoyointernacionalbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Usuario {

    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private String ciudad;
    private String pais;
    private Set<Rol> rol = new HashSet<>();
}
