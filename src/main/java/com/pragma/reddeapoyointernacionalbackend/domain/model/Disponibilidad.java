package com.pragma.reddeapoyointernacionalbackend.domain.model;

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
public class Disponibilidad {

    @NotBlank
    @NotNull
    private String fechaLlegada;
    @NotBlank
    @NotNull
    private String fechaSalida;
    private Casa casa;
}
