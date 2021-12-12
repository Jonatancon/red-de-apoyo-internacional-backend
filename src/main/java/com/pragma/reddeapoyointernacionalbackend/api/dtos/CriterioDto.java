package com.pragma.reddeapoyointernacionalbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class CriterioDto {

    private String pais;
    private String estado;
    private String ciudad;
    private String fechaInicial;
    private String fechaFinal;

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }
}
