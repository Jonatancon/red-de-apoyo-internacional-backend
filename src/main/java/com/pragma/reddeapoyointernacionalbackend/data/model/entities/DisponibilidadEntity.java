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
    @Column(name = "id_disponibilidad")
    private Integer idDisponibilidad;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_llegada")
    private LocalDate fechaLlegada;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @JoinColumn(name = "fk_usuario_reservado")
    @ManyToOne
    private UsuarioEntity usuarioReservado;

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
}
