package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadEntityTest {

    DisponibilidadEntity disponible = new DisponibilidadEntity();

    @Test
    void validar () {
        crearDisponibilidad();

        assertEquals(1, disponible.getIdDisponibilidad());
        assertEquals("12/11/2021", disponible.getFechaLlegada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertEquals("20/11/2021", disponible.getFechaSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        assertNull(disponible.getUsuarioReservado());
    }

    private void crearDisponibilidad () {
        disponible = DisponibilidadEntity.builder().usuarioReservado(null)
                .idDisponibilidad(1).fechaLlegada(LocalDate.of(2021, 11, 12))
                .fechaSalida(LocalDate.of(2021, 11, 20)).build();
    }

}