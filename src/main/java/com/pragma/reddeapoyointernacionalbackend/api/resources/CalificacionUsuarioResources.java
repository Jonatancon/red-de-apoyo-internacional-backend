package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CalificacionUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.CalificacionUserUtil;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(CalificacionUsuarioResources.CALIFICACION)
public class CalificacionUsuarioResources {

    public static final String CALIFICACION = "/api/calificacion/user";
    public static final String SAVE = "/save";
    public static final String OBTENER = "/obtener/{id}";

    @Autowired
    private CalificacionUserUtil calificacionUtil;

    @PostMapping(SAVE)
    @PreAuthorize("hasAuthority('ANFITRION')")
    public ResponseEntity<MessageDto> saveNewCalificacion (@Valid @RequestBody CalificacionUsuarioDto calificacion,
                                            BindingResult bindingResult,
                                            @RequestHeader("Authorization") String token){
        if (bindingResult.hasErrors())
            throw new RequestErrors("Missing Data", "R-001", HttpStatus.BAD_REQUEST);

        token = token.split(" ")[1];

        calificacionUtil.save(calificacion, token);

        MessageDto message = MessageDto.builder().message("Calificacion Usuario guardada")
                .code("S-001").build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(OBTENER)
    public ResponseEntity<List<CalificacionUsuarioDto>> getAllCalificacionUser(@PathVariable String id) {
        List<CalificacionUsuarioDto> calificaciones = calificacionUtil.obtenerCalificaciones(id);

        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }
}
