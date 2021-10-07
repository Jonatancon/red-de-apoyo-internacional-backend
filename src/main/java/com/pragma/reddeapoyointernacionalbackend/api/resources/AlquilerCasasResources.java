package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.AlquilerDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.TransformarDatosUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.DisponibilidadEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(AlquilerCasasResources.ALQUILER)
@CrossOrigin
public class AlquilerCasasResources {

    public static final String ALQUILER = "/viajes/alquiler";
    public static final String NUEVO_ALQUILER = "/save-alquiler";

    @Autowired
    DisponibilidadService disponibilidad;
    TransformarDatosUtil transform = new TransformarDatosUtil();

    @PostMapping(NUEVO_ALQUILER)
    @PreAuthorize("hasAnyRole('ANFITRION', 'USUARIO')")
    public ResponseEntity<MessageDto> crearNuevoAlquiler (@Valid @RequestBody AlquilerDto alquilerDto,
                                                          BindingResult bindingResult,
                                                          @RequestHeader(value = "Authorization") String token) throws Exception {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new MessageDto("Campos Incompletos"), HttpStatus.BAD_REQUEST);

        Integer idCasa = transform.decodificadorClave(alquilerDto.getIdCasa());

        if (disponibilidad.existeCasaAlquiladaEnFecha(idCasa, alquilerDto.getFechaInicio())) {
            DisponibilidadEntity nuevoAlquiler = transform.crearNuevaDisponiblidad(alquilerDto, token);
            disponibilidad.crearNuevaReserva(nuevoAlquiler);

            return new ResponseEntity<>(new MessageDto("Alquiler Guardado"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MessageDto("La Casa Ya esta Ocupada Para esa Fecha"),
                HttpStatus.BAD_REQUEST);
    }
}
