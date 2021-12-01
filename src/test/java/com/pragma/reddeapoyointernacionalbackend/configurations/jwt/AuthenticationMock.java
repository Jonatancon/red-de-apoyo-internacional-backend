package com.pragma.reddeapoyointernacionalbackend.configurations.jwt;

import com.pragma.reddeapoyointernacionalbackend.data.model.UsuarioPrincipal;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class AuthenticationMock implements Authentication {


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return UsuarioPrincipal.build(crear());
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    private UsuarioEntity crear(){
        Set<RolEntity> roles = new HashSet<>();
        roles.add(RolEntity.builder().idRol(1).nombreRol(NombreRol.ANFITRION).build());
        roles.add(RolEntity.builder().idRol(2).nombreRol(NombreRol.USUARIO).build());

        return UsuarioEntity.builder().idUsuario(1).nombreUsuario("jrstark").nombres("Jonatan Stiven")
                .apellidos("Restrepo Lora").ciudad("Medellin").pais("Colombia")
                .password("1234").rolEntity(roles).build();
    }
}
