package com.pragma.reddeapoyointernacionalbackend.infrastructure.api.resources;

import com.pragma.reddeapoyointernacionalbackend.configuration.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.domain.services.RolService;
import com.pragma.reddeapoyointernacionalbackend.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UsuarioResources.AUTH)
@CrossOrigin
public class UsuarioResources {

    public final static String AUTH = "/auth";
    public final static String NEW_USER = "/new-use";
    public final static String LOGIN = "/login";
    public final static String REFRESH_TOKEN = "/refresh-token";

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final JwtProvider jwtProvider;

    @Autowired
    public UsuarioResources(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                            UsuarioService usuarioService, RolService rolService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.jwtProvider = jwtProvider;
    }
}
