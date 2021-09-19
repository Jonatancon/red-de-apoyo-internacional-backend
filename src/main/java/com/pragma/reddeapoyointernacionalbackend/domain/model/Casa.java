package com.pragma.reddeapoyointernacionalbackend.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Casa {

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
    private Usuario usuario;
}
