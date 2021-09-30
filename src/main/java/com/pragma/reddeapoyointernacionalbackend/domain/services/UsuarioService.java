package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity crearUsuario (UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public Boolean existeNombeUsuario (String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

}
