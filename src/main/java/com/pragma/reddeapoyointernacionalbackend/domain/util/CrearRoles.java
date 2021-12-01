package com.pragma.reddeapoyointernacionalbackend.domain.util;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.domain.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrearRoles  implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) {

        if (rolService.getByNombreRol(NombreRol.ANFITRION).isEmpty()) {
            RolEntity rolAnfitrion = new RolEntity(null,NombreRol.ANFITRION);
            RolEntity rolUsuario = new RolEntity(null,NombreRol.USUARIO);
            rolService.crearRol(rolAnfitrion);
            rolService.crearRol(rolUsuario);
        }
    }
}
