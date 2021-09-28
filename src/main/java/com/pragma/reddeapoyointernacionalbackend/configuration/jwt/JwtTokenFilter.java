package com.pragma.reddeapoyointernacionalbackend.configuration.jwt;

import com.pragma.reddeapoyointernacionalbackend.domain.services.DetalleUsuarioServices;
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

    private static final Logger loggerJwtTokenFilter = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final JwtProvider jwtProvider;
    private final DetalleUsuarioServices detalleUsuarioServices;
    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    @Autowired
    public JwtTokenFilter(JwtProvider jwtProvider, DetalleUsuarioServices detalleUsuarioServices) {
        this.jwtProvider = jwtProvider;
        this.detalleUsuarioServices = detalleUsuarioServices;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = this.detalleUsuarioServices.loadUserByUsername(nombreUsuario);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e) {
            loggerJwtTokenFilter.error("Error en el metodo doFilter");
        }
    }

    private String getToken (HttpServletRequest request) {
        String header = request.getHeader(HEADER);

        if (header != null && header.startsWith(BEARER))
            return header.replace(BEARER, "");
        return null;
    }
}
