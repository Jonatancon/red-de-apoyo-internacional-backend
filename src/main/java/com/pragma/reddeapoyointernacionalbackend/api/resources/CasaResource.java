package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.ManejoDatosUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.CasaService;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(CasaResource.CASAS)
@CrossOrigin
@PreAuthorize("hasRole('ANFITRION')")
public class CasaResource {

    public static final String CASAS = "/api/casas";
    public static final String SAVE = "/guardar";

    @Autowired
    private CasaService casaService;

    private final ManejoDatosUtil manejoDatosUtil = new ManejoDatosUtil();

    @PostMapping(SAVE)
    @PreAuthorize("hasRole('ANFITRION')")
    public ResponseEntity<MessageDto> guardarCasa (@Valid @RequestBody CasaDto casaDto, BindingResult bindingResult,
                                                   @RequestHeader("Authorization") String token) {

        if (bindingResult.hasErrors())
            throw new RequestErrors("Missing data", "R-001", HttpStatus.BAD_REQUEST);

        CasaEntity casa = manejoDatosUtil.crearCasa(casaDto, token);

        casaService.crearUnaCasa(casa);

        MessageDto message = MessageDto.builder().code("S-001").message("Save Data").build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
