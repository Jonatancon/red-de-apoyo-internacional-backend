package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.CriterioDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.BusquedaCasasTransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(BusquedaCasasResource.BUSQUEDA)
public class BusquedaCasasResource {

    public static final String BUSQUEDA = "/api/casas/busqueda";
    public static final String TODAS = "/todas";
    public static final String UNICA_CASA = "/{id}";
    public static final String CRITERIOS = "/criterio";

    @Autowired
    private BusquedaCasasTransformUtil busquedaUtil;

    @GetMapping(TODAS)
    public ResponseEntity<List<CasaDto>> getAllCasas () {
        List<CasaDto> resultado = busquedaUtil.transformarBusquedaCasas();

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping(UNICA_CASA)
    public ResponseEntity<CasaDto> getHouse (@PathVariable String id) {
        CasaDto casa = busquedaUtil.buscarCasaTransform(id);
        return new ResponseEntity<>(casa, HttpStatus.OK);
    }

    @PostMapping(CRITERIOS)
    public ResponseEntity<List<CasaDto>> getAllPersonal(@RequestBody CriterioDto criterioDto) {
        List<CasaDto> result = busquedaUtil.buscarPorCriterio(criterioDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
