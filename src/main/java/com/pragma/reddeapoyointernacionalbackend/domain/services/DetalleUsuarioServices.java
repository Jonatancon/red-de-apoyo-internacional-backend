package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.domain.model.Usuario;
import com.pragma.reddeapoyointernacionalbackend.domain.model.UsuarioPrincipal;
import com.pragma.reddeapoyointernacionalbackend.domain.persistence.UsuarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleUsuarioServices implements UserDetailsService {

    private final UsuarioPersistence usuarioPersistence;

    @Autowired
    public DetalleUsuarioServices(UsuarioPersistence usuarioPersistence) {
        this.usuarioPersistence = usuarioPersistence;
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioPersistence.obtenerNombreUsuario(nombreUsuario);
        return usuario.map(UsuarioPrincipal::build).orElse(null);
    }
}
