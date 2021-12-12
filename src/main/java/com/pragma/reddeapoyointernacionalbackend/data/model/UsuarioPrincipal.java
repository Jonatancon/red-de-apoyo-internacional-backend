package com.pragma.reddeapoyointernacionalbackend.data.model;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {

    private String nombreUsuario;
    private String password;
    private String nombres;
    private String apellidos;
    private String ciudad;
    private String pais;
    private String estado;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioPrincipal build(UsuarioEntity usuarioEntity) {
        List<GrantedAuthority> authorities = usuarioEntity.getRolEntity().stream()
                .map(rolEntity -> new SimpleGrantedAuthority(rolEntity.getNombreRol().name()))
                        .collect(Collectors.toList());
        return new UsuarioPrincipal(usuarioEntity.getNombreUsuario(), usuarioEntity.getPassword(),
                usuarioEntity.getNombres(), usuarioEntity.getApellidos(),
                usuarioEntity.getCiudad(), usuarioEntity.getPais(), usuarioEntity.getEstado(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos(){return apellidos;}

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }
}
