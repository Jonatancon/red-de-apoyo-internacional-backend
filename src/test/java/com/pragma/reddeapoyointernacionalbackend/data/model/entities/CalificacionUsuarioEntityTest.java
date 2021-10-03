package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalificacionUsuarioEntityTest {

    CalificacionUsuarioEntity calificacion = new CalificacionUsuarioEntity();

    @Test
    void veriricarCalificacion () {
        crear();

        assertEquals(1, calificacion.getIdCalificacionUsuario());
        assertEquals(5, calificacion.getCalificacion());
        assertNull(calificacion.getUsuarioEntity());
        assertEquals("OK", calificacion.getComentario());
    }

    private void crear() {
        calificacion = CalificacionUsuarioEntity.builder()
                .idCalificacionUsuario(1).calificacion(5).usuarioEntity(null)
                .comentario("OK").build();
    }

}