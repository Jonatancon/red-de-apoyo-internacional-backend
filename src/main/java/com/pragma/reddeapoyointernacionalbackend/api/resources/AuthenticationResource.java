package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.AutenticacionUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
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
    private UsuarioService usuarioService;

    @Autowired
    private AutenticacionUtil autenticacionUtil;

    @PostMapping(NEW_USER)
    public ResponseEntity<MessageDto> newUser(@Valid @RequestBody
                                             UsuarioDto usuarioDto, BindingResult bindingResult) {

        if  (bindingResult.hasErrors())
            throw new RequestErrors("Missing data", "R-001", HttpStatus.BAD_REQUEST);
        if (Boolean.TRUE.equals(usuarioService.existeNombeUsuario(usuarioDto.getNombreUsuario())))
            throw new RequestErrors("This Name User Already Exists", "R-002", HttpStatus.IM_USED);

        UsuarioEntity user = autenticacionUtil.crearUsuario(usuarioDto);
        usuarioService.crearUsuario(user);

        MessageDto message = MessageDto.builder().code("S-001").message("Data Save").build();

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<JwtDto> loginUsuario (@Valid @RequestBody
                                                 LoginUsuarioDto loginUsuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestErrors("Missing data", "R-001", HttpStatus.BAD_REQUEST);

        JwtDto token = autenticacionUtil.autenticarUsuario(loginUsuarioDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(REFRESH_TOKEN)
    public ResponseEntity<JwtDto> refreshToken (@RequestBody JwtDto jwtDto) throws ParseException {

        JwtDto token = autenticacionUtil.refreshToken(jwtDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
