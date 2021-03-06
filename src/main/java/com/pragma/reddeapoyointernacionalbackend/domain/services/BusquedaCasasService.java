package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CriterioDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusquedaCasasService {

    @Autowired
    private CasaRepository casaRepository;

    public List<CasaEntity> obtenerPorPais (String pais){
        return casaRepository.findAllByPais(pais);
    }

    public List<CasaEntity> obtenerPorCiudad (String ciudad) {
        return casaRepository.findAllByCiudad(ciudad);
    }

    public List<CasaEntity> obtenerPorEstado (String estado) {
        return casaRepository.findAllByEstado(estado);
    }

    public List<CasaEntity> obtenerTodasLasCasas () {
        return casaRepository.findAll();
    }

    public Optional<CasaEntity> buscarCasa (Integer id) {
        return casaRepository.findById(id);
    }

    public List<CasaEntity> obtenerPorCriterioDeBusqueda (CriterioDto criterioDto) {
        return casaRepository.findAllByCriterial(criterioDto);
    }

    public List<CasaEntity> obtenerPorPropietario(String nombreUsuario){
        return casaRepository.findAllByUsuarioEntity_NombreUsuario(nombreUsuario);
    }

}
