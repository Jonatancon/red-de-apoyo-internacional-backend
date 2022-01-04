package com.pragma.reddeapoyointernacionalbackend.api.resources.errors;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.CommunicationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RequestErrors.class)
    public ResponseEntity<MessageDto> requestExceptionHandler(RequestErrors error) {
        MessageDto messageDto = MessageDto.builder().code(error.getCode()).message(error.getMessage()).build();
        return new ResponseEntity<>(messageDto, error.getHttpStatus());
    }

    @ExceptionHandler(value = CommunicationException.class)
    public ResponseEntity<MessageDto> communicationError() {
        MessageDto message = MessageDto.builder().code("I-001").message("Internal  Server Error").build();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class,
            InsufficientAuthenticationException.class,
            AccessDeniedException.class
    })
    public ResponseEntity<MessageDto> aunthenticationError() {
        MessageDto message = MessageDto.builder().code("A-001").message("Unauthorized").build();

        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {
            MalformedJwtException.class, IllegalArgumentException.class, ExpiredJwtException.class,
            UnsupportedJwtException.class
    })
    public ResponseEntity<MessageDto> tokenError() {
        MessageDto message = MessageDto.builder().code("T-001").message("Error in the Token").build();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<MessageDto> nullPointerEception() {
        MessageDto message = MessageDto.builder().code("D-001").message("Not Found Data").build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
