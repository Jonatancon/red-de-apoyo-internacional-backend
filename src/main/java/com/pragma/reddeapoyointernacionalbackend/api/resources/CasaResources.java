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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(CasaResources.CASA)
@CrossOrigin
public class CasaResources {
    public static final String CASA = "/viajes/casa";
    public static final String GUARDAR = "/save";
    public static final String OBTENER_TODAS_LAS_CASAS = "/alls-casas";

    @Autowired
    private BusquedasUtil busquedasUtil;

    @Autowired
    private CasaService casaService;

    @PostMapping(GUARDAR)
    @PreAuthorize("hasAnyRole('ANFITRION')")
    public ResponseEntity<MessageDto> guardarNuevaCasa(@Valid @RequestBody CasaDto casaDto,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new MessageDto("Campos Incorrectos"), HttpStatus.BAD_REQUEST);

        try{
            casaService.crearUnaCasa(crearCasaEtity(casaDto));
            return new ResponseEntity<>(new MessageDto("Casa Guardada"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageDto("Oooops... " + e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(OBTENER_TODAS_LAS_CASAS)
    public ResponseEntity<List<CasaDto>> obtenerCasas () {

        return new ResponseEntity<>(organizarLista(casaService.todasLasCasas()), HttpStatus.OK);
    }

    private List<CasaDto> organizarLista(List<CasaEntity> casas){
        List<CasaDto> addCasa = new ArrayList<>();
        casas.forEach(casaEntity -> {
            CasaDto casa =  CasaDto.builder().nombreUsuario(casaEntity.getUsuarioEntity().getNombreUsuario())
                    .ciudad(casaEntity.getCiudad()).pais(casaEntity.getPais()).direccion(casaEntity.getDireccion())
                    .telefono(casaEntity.getTelefono()).foto(casaEntity.getFoto()).build();
            addCasa.add(casa);
        });
        return addCasa;
    }

    private CasaEntity crearCasaEtity (CasaDto casaDto) {
        return CasaEntity.builder().idCasa(null).ciudad(casaDto.getCiudad()).pais(casaDto.getPais())
                .direccion(casaDto.getDireccion()).telefono(casaDto.getTelefono())
                .foto(casaDto.getFoto())
                .usuarioEntity(busquedasUtil.obtenerUsuarioEntityFromNombreUsuario(casaDto.getNombreUsuario()))
                .build();
    }

}
