package com.pragma.reddeapoyointernacionalbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {

    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsuarioPrincipal build (Usuario usuario) {
        List<GrantedAuthority> authorities =
                usuario.getRol().stream().map(rol -> new SimpleGrantedAuthority(
                        rol.getNombreRol().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombreUsuario(), usuario.getPassword(),
                usuario.getNombreUsuario(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nombreUsuario;
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
}
