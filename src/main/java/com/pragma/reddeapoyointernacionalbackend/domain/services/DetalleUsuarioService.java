package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.UsuarioPrincipal;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {

        Optional<UsuarioEntity> usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

        return usuario.map(UsuarioPrincipal::build).orElse(null);
    }
}
