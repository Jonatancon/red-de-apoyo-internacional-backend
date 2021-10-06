package com.pragma.reddeapoyointernacionalbackend.domain.negocio;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltrarPorFechasTest {

    @InjectMocks
    FiltrarPorFechas filtrarPorFechas;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void filtrarRespuestaPorFechas() {
        assertNotNull(filtrarPorFechas.filtrarRespuestaPorFechas(listaDeCasa(3), "11/12/2021"));
    }

    @Test
    void setFiltrarPorFechasVacio () {
        assertTrue(filtrarPorFechas.filtrarRespuestaPorFechas(listaDeCasa(3),
                "12/11/2021").isEmpty());
    }

    private CasaEntity crearCasaEntity(int numeroCasa) {
        return CasaEntity.builder().pais("Colombia").idCasa(numeroCasa).build();
    }

    private List<DisponibilidadEntity> listaDeCasa(int casas) {
        List<DisponibilidadEntity> addCasa = new ArrayList<>();

        for (int i = 0; i < casas; i++) {
            addCasa.add(DisponibilidadEntity.builder().idDisponibilidad(i)
                    .usuarioEntity(null).casaEntity(crearCasaEntity(i))
                    .fechaLlegada("11/12/202"+i).fechaSalida("11/12/202"+i).build()
            );
        }
        return addCasa;
    }
}