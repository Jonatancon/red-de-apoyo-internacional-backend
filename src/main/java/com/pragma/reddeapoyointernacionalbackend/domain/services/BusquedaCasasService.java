package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusquedaCasasService {

    @Autowired
    private CasaRepository casaRepository;

    @Autowired
    private DisponibilidadService disponible;

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

    public List<CasaEntity> obtenerPorFecha (String fechaReserva) {
        List<CasaEntity> casasFiltradas = casaRepository.findAll();

        disponible.obtenerCasaOcupadasPorFecha(fechaReserva)
                .forEach(casaEntity ->
                        casasFiltradas.removeIf(casa -> casa.getIdCasa().equals(casaEntity.getIdCasa())));
        return casasFiltradas;
    }
}
