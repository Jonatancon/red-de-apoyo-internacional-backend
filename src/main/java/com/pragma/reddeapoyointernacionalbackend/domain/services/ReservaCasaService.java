package com.pragma.reddeapoyointernacionalbackend.domain.services;

import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import com.pragma.reddeapoyointernacionalbackend.data.repository.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservaCasaService {

    @Autowired
    private DisponibilidadRepository reserva;

    public DisponibilidadEntity saveNewRerserved(DisponibilidadEntity newvaReserva){
        return reserva.save(newvaReserva);
    }

    public boolean disponibilidadIsTrue(Integer id, LocalDate fechaLlegada, LocalDate fechaSalida){
        List<DisponibilidadEntity> reservadas =
                reserva.findAllByCasaReservadaAndBetweenDate
                        (id, fechaLlegada, fechaSalida);

        return reservadas.isEmpty();
    }

    public List<DisponibilidadEntity> getAllRerservasByIdCasa(Integer idCasa) {
        return reserva.findAllByCasaReservada_IdCasa(idCasa);
    }

    public List<DisponibilidadEntity> getAllReservasByNombreUsuario(String nombreUsuario) {
        return reserva.findAllByUsuarioReservado_NombreUsuario(nombreUsuario);
    }
}
