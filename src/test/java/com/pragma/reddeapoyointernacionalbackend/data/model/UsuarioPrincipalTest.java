package com.pragma.reddeapoyointernacionalbackend.data.model;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioPrincipalTest {

    @InjectMocks
    UsuarioPrincipal usuarioPrincipal;

    UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        usuarioEntity = crearUsuarioMock().get();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void build() {
        assertNotNull(UsuarioPrincipal.build(usuarioEntity));
        usuarioPrincipal = UsuarioPrincipal.build(usuarioEntity);

        assertEquals(usuarioEntity.getNombreUsuario(), usuarioPrincipal.getNombreUsuario());
        assertEquals(usuarioEntity.getNombres(), usuarioPrincipal.getNombres());
        assertEquals(usuarioEntity.getApellidos(), usuarioPrincipal.getApellidos());
        assertEquals(usuarioEntity.getCiudad(), usuarioPrincipal.getCiudad());
        assertEquals(usuarioEntity.getPais(), usuarioPrincipal.getPais());
        assertEquals(usuarioEntity.getPassword(), usuarioPrincipal.getPassword());
        assertEquals(usuarioEntity.getRolEntity().size(),
                (long) usuarioPrincipal.getAuthorities().size());

        assertEquals(usuarioEntity.getNombreUsuario(), usuarioPrincipal.getUsername());
        assertTrue(usuarioPrincipal.isAccountNonExpired());
        assertTrue(usuarioPrincipal.isAccountNonLocked());
        assertTrue(usuarioPrincipal.isCredentialsNonExpired());
        assertTrue(usuarioPrincipal.isEnabled());

    }

    private Optional<UsuarioEntity> crearUsuarioMock () {
        Set<RolEntity> roles = new HashSet<>();
        roles.add(new RolEntity(1, NombreRol.ANFITRION));
        roles.add(new RolEntity(2, NombreRol.USUARIO));

        return Optional.of( UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark")
                .password("123").nombres("Jonatan Stiven").apellidos("Restrepo Lora").pais("colombia")
                .ciudad("Medellin").rolEntity(roles).build() );
    }
}