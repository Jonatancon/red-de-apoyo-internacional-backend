package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.CriterioDto;
import com.pragma.reddeapoyointernacionalbackend.domain.services.BusquedaCasasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusquedaCasasTransformUtil {

    @Autowired
    private BusquedaCasasService busqueda;


    public List<CasaDto> transformarBusquedaCasas () {
        List<CasaDto> result = new ArrayList<>();

        busqueda.obtenerTodasLasCasas().forEach(casa -> {
            CasaDto casaDto = CasaDto.builder().idCasa(casa.getIdCasa().toString())
                    .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).pais(casa.getPais())
                    .estado(casa.getEstado()).ciudad(casa.getCiudad()).direccion(casa.getDireccion())
                    .telefono(casa.getTelefono()).urlFoto(casa.getUrlFoto()).build();
            result.add(casaDto);
        });
        return result;
    }

    public CasaDto buscarCasaTransform(String id){
        return busqueda.buscarCasa(Integer.parseInt(id)).map(casa -> {
            return CasaDto.builder().idCasa(casa.getIdCasa().toString()).telefono(casa.getTelefono())
                    .direccion(casa.getDireccion()).pais(casa.getPais()).estado(casa.getEstado())
                    .ciudad(casa.getCiudad()).urlFoto(casa.getUrlFoto())
                    .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).build();
        }).orElseGet(null);
    }

    public List<CasaDto> buscarPorCriterio(CriterioDto criterio) {

        return busqueda.obtenerPorCriterioDeBusqueda(criterio).stream().map(casa ->
                CasaDto.builder().idCasa(casa.getIdCasa().toString()).pais(casa.getPais())
                        .estado(casa.getEstado()).ciudad(casa.getCiudad()).direccion(casa.getDireccion())
                        .telefono(casa.getTelefono()).urlFoto(casa.getUrlFoto())
                        .idPropietario(casa.getUsuarioEntity().getNombreUsuario()).build()
                ).collect(Collectors.toList());
    }
}
