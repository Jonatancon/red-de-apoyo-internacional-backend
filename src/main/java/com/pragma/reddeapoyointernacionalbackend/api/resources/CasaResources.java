package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.BusquedasUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.CasaEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(CasaResources.CASA)
@CrossOrigin
public class CasaResources {
    public static final String CASA = "/viajes/casa";
    public static final String GUARDAR = "/save";

    @Autowired
    private BusquedasUtil busquedasUtil;

    @Autowired
    private CasaService casaService;

    @PostMapping(GUARDAR)
    @PreAuthorize("hasAnyRole('ANFITRION')")
    public ResponseEntity<MessageDto> guardarNuevaCasa(@Valid @RequestBody CasaDto casaDto,
                                                       BindingResult bindingResult,
                                                       @RequestParam MultipartFile archivo,
                                                       @RequestHeader(value = "Authorization") String token) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new MessageDto("Campos Incorrectos"), HttpStatus.BAD_REQUEST);
        if (archivo.isEmpty())
            return new ResponseEntity<>(new MessageDto("La Foto de la casa es Obligatorio"), HttpStatus.BAD_REQUEST);

        try{
            casaService.crearUnaCasa(crearCasaEtity(casaDto, token, archivo));
            return new ResponseEntity<>(new MessageDto("Casa Guardada"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageDto("Oooops... " + e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private CasaEntity crearCasaEtity (CasaDto casaDto, String token, MultipartFile archivo) throws IOException {
        return CasaEntity.builder().idCasa(null).ciudad(casaDto.getCiudad()).pais(casaDto.getPais())
                .direccion(casaDto.getDireccion()).telefono(casaDto.getTelefono())
                .foto(archivo.getBytes()).estado(casaDto.getEstado())
                .usuarioEntity(busquedasUtil.obtenerUsuarioEntityFromNombreUsuario(token))
                .build();
    }

}
