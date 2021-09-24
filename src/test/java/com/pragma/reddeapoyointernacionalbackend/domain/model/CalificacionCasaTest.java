package com.pragma.reddeapoyointernacionalbackend.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalificacionCasaTest {

    CalificacionCasa calificacionCasa;
    CalificacionCasa calificacionCasa1 = new CalificacionCasa();

    @BeforeEach
    void setUp() {
        calificacionCasa1.setCasa(null);
        calificacionCasa1.setCalificacionAnfitrion(5);
        calificacionCasa1.setCalificacionHospedaje(3);
        calificacionCasa1.setComentario("OK");

        calificacionCasa = CalificacionCasa.builder()
                .calificacionAnfitrion(5)
                .calificacionHospedaje(4)
                .comentario("OK")
                .casa(null)
                .build();
    }

    @Test
    void comprobarClase() {
        Assertions.assertEquals(5, calificacionCasa.getCalificacionAnfitrion());
        Assertions.assertEquals(4, calificacionCasa.getCalificacionHospedaje());
        Assertions.assertEquals("OK", calificacionCasa.getComentario());
        Assertions.assertNull(calificacionCasa.getCasa());
        Assertions.assertNotEquals(calificacionCasa, calificacionCasa1);
    }

}