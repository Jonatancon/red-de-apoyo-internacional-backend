package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.RolEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.NombreRol;
import com.pragma.reddeapoyointernacionalbackend.data.repository.RolRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepositoy rolRepositoy;

    public Optional<RolEntity> getByNombreRol (NombreRol nombreRol) {
        return rolRepositoy.findByNombreRol(nombreRol);
    }

    public void crearRol (RolEntity rolEntity) {
        rolRepositoy.save(rolEntity);
    }
}
