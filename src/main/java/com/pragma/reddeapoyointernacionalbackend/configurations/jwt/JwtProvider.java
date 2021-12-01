package com.pragma.reddeapoyointernacionalbackend.configurations.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import com.pragma.reddeapoyointernacionalbackend.data.model.UsuarioPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    private final String secret;

    private final Long expiration;

    public JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }


    private static final String ROLES = "roles";

    public String generarToken (Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        List<String> roles = usuarioPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return Jwts.builder().setSubject(usuarioPrincipal.getNombreUsuario())
                .setIssuedAt(new Date())
                .claim(ROLES, roles)
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public String getNombreUsuarioFromToken (String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken (String token) {
        try{
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e) {
            log.error("Token mal formado");
        }catch (ExpiredJwtException e) {
            log.error("Token Expirado");
        }catch (IllegalArgumentException e) {
            log.error("Token Ilegal");
        }
        return false;
    }

    public String refreshToken (JwtDto jwtDto) throws ParseException {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim(ROLES);

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim(ROLES, roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                    .compact();
        }
        return "Opsss.... algo salio mal comprueba tu token";
    }
}
