package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.AlquilerDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class TransformarDatosUtil {

    private CodificadorUtil codificador = new CodificadorUtil();
    private BusquedasUtil buscar = new BusquedasUtil();

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
                addCasa.add(CasaDto.builder().idCasa("Error....Clave").build());
            }
        });
        return addCasa;
    }

    private UsuarioDto crearUsuarioDto (UsuarioEntity usuario) {
        return UsuarioDto.builder().pais(usuario.getPais()).ciudad(usuario.getCiudad())
                .nombreCompleto(usuario.getNombreCompleto()).build();
    }

    public Integer decodificadorClave(String id) throws Exception {
        return Integer.parseInt(codificador.decrypt(id));
    }

    public DisponibilidadEntity crearNuevaDisponiblidad(AlquilerDto alquilerDto, String token) throws Exception {
        Integer idCasa = decodificadorClave(alquilerDto.getIdCasa());

        CasaEntity casa = buscar.encontrarCasa(idCasa);
        UsuarioEntity user = buscar.obtenerUsuarioEntityFromNombreUsuario(token);

        return DisponibilidadEntity.builder().fechaLlegada(alquilerDto.getFechaInicio())
                .fechaSalida(alquilerDto.getFechaFinal()).casaEntity(casa)
                .usuarioEntity(user).idDisponibilidad(null).build();
    }

}
