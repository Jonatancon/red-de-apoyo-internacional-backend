package com.pragma.reddeapoyointernacionalbackend.data.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "disponibilidad")
public class DisponibilidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Integer idDisponibilidad;

    @Column(name = "fecha_llegada")
    private LocalDate fechaLlegada;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @JoinColumn(name = "fk_usuario_reservado")
    @ManyToOne
    private UsuarioEntity usuarioReservado;

    @JoinColumn(name = "fk_casa_reservada")
    @ManyToOne
    private CasaEntity casaReservada;

    private boolean calificoUsuario;

    private boolean calificoAnfritrion;

    public UsuarioEntity getUsuarioReservado() {
        return usuarioReservado;
    }

    public Integer getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public boolean isCalificoUsuario() {
        return calificoUsuario;
    }

    public boolean isCalificoAnfritrion() {
        return calificoAnfritrion;
    }

    public CasaEntity getCasaReservada() {
        return casaReservada;
    }
}
