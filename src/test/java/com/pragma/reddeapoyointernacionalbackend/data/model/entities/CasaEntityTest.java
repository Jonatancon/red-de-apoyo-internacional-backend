package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasaEntityTest {

    CasaEntity casa = new CasaEntity();

    @Test
    void verificarCasa () {
        crearCasa();

        assertEquals(1, casa.getIdCasa());
        assertEquals("Medellin", casa.getCiudad());
        assertEquals("Colombia", casa.getPais());
        assertEquals("123", casa.getTelefono());
        assertNull(casa.getFoto());
        assertNull(casa.getUsuarioEntity());
        assertEquals("2020", casa.getDireccion());
    }

    private void crearCasa () {
        casa = CasaEntity.builder().idCasa(1).ciudad("Medellin").pais("Colombia")
                .telefono("123").direccion("2020").foto(null).usuarioEntity(null).build();
    }

}