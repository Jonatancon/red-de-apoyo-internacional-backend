package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity crearUsuario (UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public boolean existeNombeUsuario (String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public Optional<UsuarioEntity> getUsuarioFromNombreUsuario (String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public void updateUser(String userName) {
        UsuarioEntity user = usuarioRepository.findByNombreUsuario(userName).orElse(null);
        Set<RolEntity> rols = new HashSet<>();
        rols.add(RolEntity.builder().idRol(1).nombreRol(NombreRol.ANFITRION).build());
        rols.add(RolEntity.builder().idRol(2).nombreRol(NombreRol.USUARIO).build());

        user.setRolEntity(rols);

        usuarioRepository.save(user);
    }

}
