package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.AutenticacionUtil;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(AuthenticationResource.AUTH)
@CrossOrigin
public class AuthenticationResource {

    public static final String AUTH = "/auth";
    public static final String NEW_USER = "/new-user";
    public static final String LOGIN = "/login";
    public static final String REFRESH_TOKEN = "/refresh-token";

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AutenticacionUtil autenticacionUtil;

    @PostMapping(NEW_USER)
    public ResponseEntity<MessageDto> newUser(@Valid @RequestBody
                                             UsuarioDto usuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new MessageDto("Campos mal puestos"), HttpStatus.BAD_REQUEST);

        if (Boolean.TRUE.equals(usuarioService.existeNombeUsuario(usuarioDto.getNombreUsuario()))) // cambio si no cambiar a tipo de dato normal
            return new ResponseEntity<>(new MessageDto("Ya se encuentra en uso"), HttpStatus.BAD_REQUEST);

        try{
            usuarioService.crearUsuario(autenticacionUtil.crearUsuario(usuarioDto));
            return new ResponseEntity<>(new MessageDto("Usuario Guardado"),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageDto("Oooops... " + e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(LOGIN)
    public ResponseEntity<JwtDto> loginUsuario (@Valid @RequestBody
                                                 LoginUsuarioDto loginUsuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new JwtDto("Campos Mal Puestos"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(autenticacionUtil.autenticarUsuario(loginUsuarioDto),HttpStatus.OK);
    }

    @PostMapping(REFRESH_TOKEN)
    public ResponseEntity<JwtDto> refreshToken (@Valid @RequestBody
                                                            JwtDto jwtDto, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new JwtDto("Sin Token"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(autenticacionUtil.refreshToken(jwtDto), HttpStatus.OK);
    }
}
