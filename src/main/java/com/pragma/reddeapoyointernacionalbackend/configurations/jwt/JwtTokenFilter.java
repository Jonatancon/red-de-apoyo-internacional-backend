package com.pragma.reddeapoyointernacionalbackend.configurations.jwt;

import com.pragma.reddeapoyointernacionalbackend.domain.services.DetalleUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger loggerTokenFilter = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private DetalleUsuarioService detalleUsuarioService;

    private static final String BEARER = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);

            if (token != null && jwtProvider.validateToken(token)) {
                String nombreUsuario =jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails =detalleUsuarioService.loadUserByUsername(nombreUsuario);

                UsernamePasswordAuthenticationToken aut =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(aut);
            }
        }catch (Exception e){
            loggerTokenFilter.error("Fallo en el metodo Dofilter");
        }

        filterChain.doFilter(request, response);
    }

    private String getToken (HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        if (header != null && header.startsWith(BEARER))
            return header.replace(BEARER, "");

        return null;
    }
}
