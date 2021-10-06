package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.DisponibilidadRepository;
import com.pragma.reddeapoyointernacionalbackend.domain.negocio.FiltrarPorFechas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadService {

    private FiltrarPorFechas filtrar = new FiltrarPorFechas();

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    public List<CasaEntity> obtenerCasaOcupadasPorFecha (String fechaBuscar) {
        return filtrar.filtrarRespuestaPorFechas(disponibilidadRepository.findAll(), fechaBuscar);
    }

}
