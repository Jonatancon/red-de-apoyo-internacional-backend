package com.pragma.reddeapoyointernacionalbackend.api.resources;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.LoginUsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.UsuarioDto;
import com.pragma.reddeapoyointernacionalbackend.api.resources.util.AutenticacionUtil;
import com.pragma.reddeapoyointernacionalbackend.data.model.entities.UsuarioEntity;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.CodeError;
import com.pragma.reddeapoyointernacionalbackend.data.model.enums.CodeInfos;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public static final String DATA_USER = "/data-user/{id}";
    public static final String UPDATE = "/update";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutenticacionUtil autenticacionUtil;

    @PostMapping(NEW_USER)
    public ResponseEntity<MessageDto> newUser(@Valid @RequestBody
                                             UsuarioDto usuarioDto, BindingResult bindingResult) {

        if  (bindingResult.hasErrors())
            throw new RequestErrors("Missing data", CodeError.R_001.name(), HttpStatus.BAD_REQUEST);
        if (Boolean.TRUE.equals(usuarioService.existeNombeUsuario(usuarioDto.getNombreUsuario())))
            throw new RequestErrors("This Name User Already Exists",
                    CodeError.R_002.name(), HttpStatus.BAD_REQUEST);

        UsuarioEntity user = autenticacionUtil.crearUsuario(usuarioDto);
        usuarioService.crearUsuario(user);

        MessageDto message = MessageDto.builder().code(CodeInfos.S_001.name()).message("Data Save").build();

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<JwtDto> loginUsuario (@Valid @RequestBody
                                                 LoginUsuarioDto loginUsuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestErrors("Missing data", CodeError.R_001.name(), HttpStatus.BAD_REQUEST);

        JwtDto token = autenticacionUtil.autenticarUsuario(loginUsuarioDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(REFRESH_TOKEN)
    public ResponseEntity<JwtDto> refreshToken (@RequestBody JwtDto jwtDto) throws ParseException {

        JwtDto token = autenticacionUtil.refreshToken(jwtDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping(DATA_USER)
    public ResponseEntity<UsuarioDto> getDataUser(@PathVariable String id) {

        if (!usuarioService.existeNombeUsuario(id))
            throw new RequestErrors("Not Data", CodeError.R_001.name(), HttpStatus.BAD_REQUEST);

       UsuarioDto response =  usuarioService.getUsuarioFromNombreUsuario(id).map(user ->  UsuarioDto.builder()
                .apellidos(user.getApellidos()).nombres(user.getNombres())
                .ciudad(user.getCiudad()).pais(user.getPais()).estado(user.getEstado())
                .build()).orElse(null);

       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(UPDATE)
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<MessageDto> updateUSer(@RequestHeader("Authorization") String token){
        token = token.split(" ")[1];

        String userName =  autenticacionUtil.updateUser(token);

        usuarioService.updateUser(userName);

        MessageDto message = MessageDto.builder().code(CodeInfos.U_001.name()).message("Usuario Update").build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
