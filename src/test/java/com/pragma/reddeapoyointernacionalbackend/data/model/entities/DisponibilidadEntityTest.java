package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadEntityTest {

    DisponibilidadEntity disponible = new DisponibilidadEntity();

    @Test
    void validar () {
        crearDisponibilidad();

        assertEquals(1, disponible.getIdDisponibilidad());
        assertEquals("12/11/2021", disponible.getFechaLlegada());
        assertEquals("20/11/2021", disponible.getFechaSalida());
        assertNull(disponible.getCasaEntity());
        assertNull(disponible.getUsuarioEntity());
    }

    private void crearDisponibilidad () {
        disponible = DisponibilidadEntity.builder().usuarioEntity(null)
                .idDisponibilidad(1).casaEntity(null).fechaLlegada("12/11/2021")
                .fechaSalida("20/11/2021").build();
    }

}