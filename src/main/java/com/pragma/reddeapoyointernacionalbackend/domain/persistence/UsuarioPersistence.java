package com.pragma.reddeapoyointernacionalbackend.domain.persistence;

import com.pragma.reddeapoyointernacionalbackend.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioPersistence {
    Optional<Usuario> obtenerNombreUsuario (String nombreUsuario);
    void crearUsuario (Usuario usuario);
    Boolean existeNombreUsuario (String nombreUsuario);

}
