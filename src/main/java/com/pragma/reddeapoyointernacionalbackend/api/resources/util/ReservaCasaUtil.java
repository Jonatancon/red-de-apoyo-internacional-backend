package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.DisponibilidadDto;
import com.pragma.reddeapoyointernacionalbackend.configurations.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.BusquedaCasasService;
import com.pragma.reddeapoyointernacionalbackend.domain.services.ReservaCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaCasaUtil {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ReservaCasaService reserva;
    @Autowired
    private ManejoDatosUtil manejoDatosUtil;
    @Autowired
    private BusquedaCasasService busquedaCasasService;


    public boolean isDisponible(DisponibilidadDto disponibilidad) {
        Integer id = Integer.valueOf(disponibilidad.getiDcasa());
        LocalDate fechaI = disponibilidad.getFechaInicial();
        LocalDate fechaF = disponibilidad.getFechaFinal();

        return reserva.disponibilidadIsTrue(id, fechaI, fechaF);
    }

    public DisponibilidadDto saveReserva(String token, DisponibilidadDto form) {
        String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
        UsuarioEntity user = manejoDatosUtil.buscarUsuario(nombreUsuario);
        CasaEntity casa = busquedaCasasService.buscarCasa(Integer.valueOf(form.getiDcasa())).orElse(null);

        DisponibilidadEntity newReserva = DisponibilidadEntity.builder().casaReservada(casa)
                .calificoUsuario(false).calificoAnfritrion(false).usuarioReservado(user)
                .fechaLlegada(form.getFechaInicial()).fechaSalida(form.getFechaFinal())
                .build();

        DisponibilidadEntity result = reserva.saveNewRerserved(newReserva);

        return DisponibilidadDto.builder().fechaInicial(result.getFechaLlegada())
                .fechaFinal(result.getFechaSalida())
                .usuarioReserver(result.getUsuarioReservado().getNombreUsuario()).build();
    }

    public List<DisponibilidadDto> obtenerReservasByIdCasa(String id){

        return reserva.getAllRerservasByIdCasa(Integer.valueOf(id)).stream().map(reserva ->
            DisponibilidadDto.builder().usuarioReserver(reserva.getUsuarioReservado().getNombreUsuario())
                    .iDcasa(reserva.getCasaReservada().getIdCasa().toString())
                    .fechaInicial(reserva.getFechaLlegada()).fechaFinal(reserva.getFechaSalida())
                    .calificoAnfitrion(reserva.isCalificoAnfritrion()).calificoUsuario(reserva.isCalificoUsuario())
                    .build()
        ).collect(Collectors.toList());
    }
}
