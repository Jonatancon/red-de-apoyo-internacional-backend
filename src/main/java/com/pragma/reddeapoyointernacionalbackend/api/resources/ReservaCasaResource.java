package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.DisponibilidadDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.ReservaCasaUtil;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(ReservaCasaResource.RESERVA)
public class ReservaCasaResource {

     public static final String RESERVA = "api/reserva";
     public static final String GUARDAR = "/save";
     public static final String RESERVAS_CASA = "/obtener/{id}";

     @Autowired
     private ReservaCasaUtil reserva;

     @PostMapping(GUARDAR)
     public ResponseEntity<DisponibilidadDto> saveNewReserva(@Valid @RequestBody DisponibilidadDto disponibilidadDto,
                                                             BindingResult bindingResult,
                                                             @RequestHeader("Authorization") String token ) {
         if (bindingResult.hasErrors())
              throw new RequestErrors("Missing Data", "R-001", HttpStatus.BAD_REQUEST);

         if (!reserva.isDisponible(disponibilidadDto))
              throw new RequestErrors("Not Available Date", "R-003", HttpStatus.BAD_REQUEST );

         token = token.split(" ")[1];

         DisponibilidadDto result = reserva.saveReserva(token, disponibilidadDto);

         return new ResponseEntity<>(result, HttpStatus.OK);
     }

     @GetMapping(RESERVAS_CASA)
    public ResponseEntity<List<DisponibilidadDto>> obtenerReservas(@PathVariable String id){
        List<DisponibilidadDto>result = reserva.obtenerReservasByIdCasa(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
     }

}
