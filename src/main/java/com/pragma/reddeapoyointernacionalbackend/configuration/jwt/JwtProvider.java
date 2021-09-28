package com.pragma.reddeapoyointernacionalbackend.configuration.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.pragma.reddeapoyointernacionalbackend.domain.model.UsuarioPrincipal;
import com.pragma.reddeapoyointernacionalbackend.infrastructure.api.dtos.JwtDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private static final String ROLES = "roles";

    public String generarToken (Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        List<String> roles = usuarioPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return Jwts.builder().setSubject(usuarioPrincipal.getNombreUsuario())
                .setIssuedAt(new Date())
                .claim(ROLES, roles)
                .setExpiration(new Date(new Date().getTime() + this.expiration))
                .signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
                .compact();
    }

    public String getNombreUsuarioFromToken (String token) {
        return Jwts.parser().setSigningKey(this.secret.getBytes())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Boolean validateToken (String token) {
        try {
           Jwts.parser().setSigningKey(this.secret.getBytes())
                   .parseClaimsJws(token);
           return true;
        }catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        }catch (UnsupportedJwtException e) {
            logger.error("Token no Soportado");
        }catch (ExpiredJwtException e) {
            logger.error("Token Expirado");
        }catch (IllegalArgumentException e) {
            logger.error("Token Ilegal");
        }catch (SignatureException e) {
            logger.error("Fail en la firma");
        }
        return false;
    }

    public String refreshToken (JwtDto jwtDto) throws ParseException {
        JWT jwt = JWTParser.parse(jwtDto.getToken());
        JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
        String dni = claimsSet.getSubject();
        List<String> roles = (List<String>) claimsSet.getClaim(ROLES);

        return Jwts.builder().setSubject(dni)
                .setIssuedAt(new Date())
                .claim(ROLES, roles)
                .setExpiration(new Date(new Date().getTime() + this.expiration))
                .signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
                .compact();
    }
}
