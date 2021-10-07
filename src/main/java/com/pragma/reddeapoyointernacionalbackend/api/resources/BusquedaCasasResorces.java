package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.CasaDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.TransformarDatosUtil;
import com.pragma.reddeapoyointernacionalbackend.domain.services.BusquedaCasasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BusquedaCasasResorces.BUSQUEDA_CENTRAL + BusquedaCasasResorces.BUSQUEDA)
@CrossOrigin
public class BusquedaCasasResorces {

    public static final String BUSQUEDA_CENTRAL = "/viajes";
    public static final String BUSQUEDA = "/busquedas/casas";
    public static final String B_PAIS = "/pais/{pais}";
    public static final String B_CIUDAD = "/ciudad/{ciudad}";
    public static final String B_ESTADO = "/estado/{estado}";
    public static final String B_FECHA = "/fecha/{fecha}";

    @Autowired
    private BusquedaCasasService busquedaCasasService;

    private final TransformarDatosUtil dataTranform = new TransformarDatosUtil();

    @GetMapping(BUSQUEDA_CENTRAL + BUSQUEDA)
    public ResponseEntity<List<CasaDto>> buscarTodasLasCasas() {

        return new ResponseEntity<>(dataTranform.listaCasasDto(busquedaCasasService.obtenerTodasLasCasas()),
                HttpStatus.OK);
    }

    @GetMapping(BUSQUEDA + B_PAIS)
    public ResponseEntity<List<CasaDto>> bucarPorPais (@PathVariable String pais) {

        return new ResponseEntity<>(dataTranform.listaCasasDto(busquedaCasasService.obtenerPorPais(pais)),
                HttpStatus.OK);
    }

    @GetMapping(BUSQUEDA + B_CIUDAD)
    public ResponseEntity<List<CasaDto>> bucarPorCiudad (@PathVariable String ciudad) {
        return new ResponseEntity<>(dataTranform.listaCasasDto(busquedaCasasService.obtenerPorCiudad(ciudad)),
                HttpStatus.OK);
    }

    @GetMapping(BUSQUEDA + B_ESTADO)
    public ResponseEntity<List<CasaDto>> bucarPorEstado (@PathVariable String estado) {
        return new ResponseEntity<>(dataTranform.listaCasasDto(busquedaCasasService.obtenerPorEstado(estado)),
                HttpStatus.OK);
    }

    @GetMapping(BUSQUEDA + B_FECHA)
    public ResponseEntity<List<CasaDto>> bucarPorFecha (@PathVariable String fecha) {
        return new ResponseEntity<>(dataTranform.listaCasasDto(busquedaCasasService.obtenerPorFecha(fecha)),
                HttpStatus.OK);
    }

}
