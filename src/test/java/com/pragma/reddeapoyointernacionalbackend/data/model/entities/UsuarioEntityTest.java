package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    @Test
    void cargarEntidad () {
        assertEquals("stark", crearUsuario().getNombreUsuario());
        assertEquals("password", crearUsuario().getPassword());
    }

    private UsuarioEntity crearUsuario() {
        UsuarioEntity user = new UsuarioEntity();
        user.setNombreUsuario("stark");
        user.setPassword("password");
        return user;
    }

}