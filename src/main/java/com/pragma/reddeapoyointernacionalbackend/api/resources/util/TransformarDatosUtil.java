package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class TransformarDatosUtil {

    private CodificadorUtil codificador = new CodificadorUtil();

    public List<CasaDto> listaCasasDto (List<CasaEntity> casasEntity) {
        List<CasaDto> addCasa = new ArrayList<>();

        casasEntity.forEach(casa -> {
            try {
                addCasa.add(CasaDto.builder().idCasa(codificador.encript(casa.getIdCasa().toString()))
                        .ciudad(casa.getCiudad()).pais(casa.getPais()).estado(casa.getEstado())
                        .telefono(casa.getTelefono()).direccion(casa.getDireccion())
                        .usuarioDto(crearUsuarioDto(casa.getUsuarioEntity())).foto(casa.getFoto()).build()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return addCasa;
    }

    private UsuarioDto crearUsuarioDto (UsuarioEntity usuario) {
        return UsuarioDto.builder().pais(usuario.getPais()).ciudad(usuario.getCiudad())
                .nombreCompleto(usuario.getNombreCompleto()).build();
    }

}
