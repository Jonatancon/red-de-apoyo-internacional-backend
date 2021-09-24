package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.persistence;

import com.pragma.reddeapoyointernacionalbackend.domain.model.Rol;
import com.pragma.reddeapoyointernacionalbackend.domain.model.Usuario;
import com.pragma.reddeapoyointernacionalbackend.domain.persistence.UsuarioPersistence;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos.UsuarioDao;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Repository
public class UsuarioPersistenceMySqlDb implements UsuarioPersistence {

    public UsuarioDao usuarioDao;

    @Autowired
    public UsuarioPersistenceMySqlDb(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Optional<Usuario> obtenerNombreUsuario(String nombreUsuario) {
        UsuarioEntity usuarioEntity = usuarioDao.findByNombreUsuario(nombreUsuario).get();

        return Optional.of(Usuario.builder().nombreUsuario(usuarioEntity.getNombreUsuario())
                .nombreCompleto(usuarioEntity.getNombreCompleto())
                .password(usuarioEntity.getPassword())
                .ciudad(usuarioEntity.getCiudad())
                .pais(usuarioEntity.getPais())
                .rol(obtenerRoles(usuarioEntity))
                .build());
    }

    @Override
    public void crearUsuario(Usuario usuario) {

    }

    @Override
    public Boolean existeNombreUsuario(String nombreUsuario) {
        return null;
    }

    private Set<Rol> obtenerRoles(UsuarioEntity usuarioEntity) {
        Set<Rol> roles = new HashSet<>();
        Rol rols = new Rol();

        usuarioEntity.getRolEntity().forEach(rolEntity -> {
            rols.setNombreRol(rolEntity.getNombreRol());
            roles.add(rols);
        });

        return roles;
    }
}
