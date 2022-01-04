package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CalificacionCasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.CalificacionCasaUtil;
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
@RequestMapping(CalificacionCasasResources.CALIFICACION)
public class CalificacionCasasResources {

    public static final String CALIFICACION = "/api/calificacion";
    public static final String SAVE = "/save";
    public static final String OBTENER_ID_CASA = "/obtener/{id}";

    @Autowired
    private CalificacionCasaUtil calificacionCasaUtil;

    @PostMapping(SAVE)
    public ResponseEntity<MessageDto> saveNewCalificacion(@Valid @RequestBody CalificacionCasaDto calificacion,
                                                          BindingResult bindingResult,
                                                          @RequestHeader("Authorization") String token){
        if (bindingResult.hasErrors())
            throw new RequestErrors("Missing Data", "R-001", HttpStatus.BAD_REQUEST);

        token = token.split(" ")[1];

        calificacionCasaUtil.guardarCalificacion(calificacion, token);
        MessageDto message = new MessageDto("S-001", "Calificacios its save");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(OBTENER_ID_CASA)
    public ResponseEntity< List<CalificacionCasaDto> > getAllCalificaciones(@PathVariable String id) {

        List<CalificacionCasaDto> calificaciones = calificacionCasaUtil.todasCalificaciones(id);

        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

}
