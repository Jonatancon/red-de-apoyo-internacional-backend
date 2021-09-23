package com.pragma.reddeapoyointernacionalbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CalificacionCasa {

    private Casa casa;
    private String comentario;
    private Integer calificacionHospedaje;
    private Integer calificacionAnfitrion;
}
