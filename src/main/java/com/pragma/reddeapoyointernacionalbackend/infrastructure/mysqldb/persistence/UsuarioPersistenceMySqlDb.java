package com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.persistence;

import com.pragma.reddeapoyointernacionalbackend.domain.model.Rol;
import com.pragma.reddeapoyointernacionalbackend.domain.model.Usuario;
import com.pragma.reddeapoyointernacionalbackend.domain.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.persistence.UsuarioPersistence;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos.RolDao;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.daos.UsuarioDao;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.mysqldb.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Repository
public class UsuarioPersistenceMySqlDb implements UsuarioPersistence {

    private final UsuarioDao usuarioDao;
    private final RolDao rolDao;

    @Autowired
    public UsuarioPersistenceMySqlDb(UsuarioDao usuarioDao, RolDao rolDao) {
        this.usuarioDao = usuarioDao;
        this.rolDao = rolDao;
    }

    @Override
    public Optional<Usuario> obtenerNombreUsuario(String nombreUsuario) {
        Optional<UsuarioEntity> usuarioEntity = usuarioDao.findByNombreUsuario(nombreUsuario);
        return usuarioEntity.map(entity -> Usuario.builder().nombreUsuario(entity.getNombreUsuario())
                .nombreCompleto(entity.getNombreCompleto())
                .password(entity.getPassword())
                .ciudad(entity.getCiudad())
                .pais(entity.getPais())
                .rol(obtenerRoles(entity))
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
        Set<RolEntity> roles = new HashSet<>();

        if (usuario.getRol().contains(Rol.builder().nombreRol(NombreRol.ANFITRION).build())) {
            rolDao.findByNombreRol(NombreRol.USUARIO).ifPresent(roles::add);
            rolDao.findByNombreRol(NombreRol.ANFITRION).ifPresent(roles::add);
        }else{
            rolDao.findByNombreRol(NombreRol.USUARIO).ifPresent(roles::add);
        }

        return UsuarioEntity.builder().idUsuario(null)
                .nombreUsuario(usuario.getNombreUsuario()).nombreCompleto(usuario.getNombreCompleto())
                .password(usuario.getPassword()).pais(usuario.getPais()).ciudad(usuario.getCiudad())
                .rolEntity(roles).build();
    }
}
