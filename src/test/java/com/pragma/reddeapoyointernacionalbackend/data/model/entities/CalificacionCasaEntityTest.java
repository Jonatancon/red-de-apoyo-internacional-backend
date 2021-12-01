package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalificacionCasaEntityTest {

    CalificacionCasaEntity calificacionCasa = new CalificacionCasaEntity();

    @Test
    void validarCalificacionCasa(){
        crearCalificacion();

        assertEquals(1, calificacionCasa.getIdCalificacionCasa());
        assertEquals(5, calificacionCasa.getCalificacionAnfitrion());
        assertEquals(4, calificacionCasa.getCalificacionCasa());
        assertEquals("OK", calificacionCasa.getComentario());
        assertNull(calificacionCasa.getCasa());
    }

    private void crearCalificacion() {
        calificacionCasa = CalificacionCasaEntity.builder()
                .idCalificacionCasa(1).calificacionAnfitrion(5).calificacionCasa(4)
                .comentario("OK").casa(null).build();
    }

}