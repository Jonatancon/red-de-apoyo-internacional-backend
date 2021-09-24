package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.persistence;

import com.pragma.reddeapoyointernacionalbackend.domain.model.Usuario;
import com.pragma.reddeapoyointernacionalbackend.domain.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos.UsuarioDao;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.UsuarioEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class UsuarioPersistenceMySqlDbTest {

    private final UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);

    RolEntity rols;
    RolEntity rols1;
    Set<RolEntity> rol = new HashSet<>();
    UsuarioEntity usuarioEntity = new UsuarioEntity();

    @Autowired
    private UsuarioPersistenceMySqlDb usuarioPersistenceMySqlDb = new UsuarioPersistenceMySqlDb(usuarioDao);

    @BeforeEach
    void setUp() {
        Mockito.when(usuarioDao.findByNombreUsuario("JRSTARK"))
                .thenReturn(Optional.of(crearUsuario()));
    }

    @Test
    void obtenerNombreUsuario() {
        Optional<Usuario> result = usuarioPersistenceMySqlDb.obtenerNombreUsuario("JRSTARK");
        Assertions.assertEquals("JRSTARK", result.get().getNombreUsuario());
        Assertions.assertEquals("Jonatan Restrepo", result.get().getNombreCompleto());
        Assertions.assertEquals("1234", result.get().getPassword());
        Assertions.assertEquals("colombia", result.get().getPais());
        Assertions.assertEquals("medellin", result.get().getCiudad());
        Assertions.assertEquals("[Rol(nombreRol=USUARIO), Rol(nombreRol=USUARIO)]",
                new ArrayList<>(result.get().getRol()).toString());
    }

    UsuarioEntity crearUsuario() {
        usuarioEntity.setIdUsuario(1);
        usuarioEntity.setNombreUsuario("JRSTARK");
        usuarioEntity.setNombreCompleto("Jonatan Restrepo");
        usuarioEntity.setPassword("1234");
        usuarioEntity.setPais("colombia");
        usuarioEntity.setCiudad("medellin");
        usuarioEntity.setRolEntity(crearRoles());

        return usuarioEntity;
    }

    Set<RolEntity> crearRoles() {
        rols = new RolEntity(1, NombreRol.USUARIO);
        rol.add(rols);
        rols1 = new RolEntity(2, NombreRol.ANFITRION);
        rol.add(rols1);

        return rol;
    }
}