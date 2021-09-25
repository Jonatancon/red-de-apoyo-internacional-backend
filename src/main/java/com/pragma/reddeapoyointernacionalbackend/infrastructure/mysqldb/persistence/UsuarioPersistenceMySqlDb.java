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

    private final UsuarioDao usuarioDao;

    @Autowired
    public UsuarioPersistenceMySqlDb(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Optional<Usuario> obtenerNombreUsuario(String nombreUsuario) {
        if (usuarioDao.findByNombreUsuario(nombreUsuario).isEmpty()) {
            return Optional.empty();
        }

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
        usuarioDao.save(crearUsuarioEntity(usuario));
    }

    @Override
    public Boolean existeNombreUsuario(String nombreUsuario) {
        return usuarioDao.existsByNombreUsuario(nombreUsuario);
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

    private UsuarioEntity crearUsuarioEntity (Usuario usuario) {

        return UsuarioEntity.builder().idUsuario(null)
                .nombreUsuario(usuario.getNombreUsuario()).nombreCompleto(usuario.getNombreCompleto())
                .password(usuario.getPassword()).pais(usuario.getPais()).ciudad(usuario.getCiudad())
                .rolEntity(null).build();
    }
}
