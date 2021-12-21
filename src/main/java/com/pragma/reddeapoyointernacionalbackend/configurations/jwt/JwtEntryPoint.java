package com.pragma.reddeapoyointernacionalbackend.configurations.jwt;

import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {

        log.error("error en el metodo commons " + e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
