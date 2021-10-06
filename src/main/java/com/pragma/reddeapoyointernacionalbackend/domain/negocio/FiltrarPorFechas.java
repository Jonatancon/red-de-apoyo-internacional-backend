package com.pragma.reddeapoyointernacionalbackend.domain.negocio;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;

import java.util.ArrayList;
import java.util.List;

public class FiltrarPorFechas {

    public List<CasaEntity> filtrarRespuestaPorFechas (List<DisponibilidadEntity> disponibilidad,
                                                       String fechaFiltro) {
        List<CasaEntity> addDisponibilidad = new ArrayList<>();
        String[] fechaFiltrada = fechaFiltro.split("/");

        disponibilidad.forEach(casa -> {
            String[] fechaInicio = casa.getFechaLlegada().split("/");
            String[] fechaFinal = casa.getFechaSalida().split("/");

            if (aceptado(fechaInicio, fechaFinal, fechaFiltrada))
                addDisponibilidad.add(casa.getCasaEntity());
        });

        return addDisponibilidad;
    }

    private boolean aceptado(String[] fechaInicio, String[] fechaFinal, String[] fechaFiltrada) {
        long rangoInfererior, rangoSuperior, numero;

        rangoInfererior = Integer.parseInt(fechaInicio[2] + fechaInicio[1] + fechaInicio[0]);
        rangoSuperior = Integer.parseInt(fechaFinal[2] + fechaFinal[1] + fechaFinal[0]);
        numero = Integer.parseInt(fechaFiltrada[2] + fechaFiltrada[1] + fechaFiltrada[0]);

        if (rangoInfererior <= numero && numero <= rangoSuperior)
            return true;
        return false;
    }
}
