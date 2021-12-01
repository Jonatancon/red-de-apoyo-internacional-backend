package com.pragma.reddeapoyointernacionalbackend.api.resources.errors;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.MessageDto;
import com.pragma.reddeapoyointernacionalbackend.http_errors.RequestErrors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAdviceTest {

   private RequestErrors requestErrors = new RequestErrors("OK", "OK", HttpStatus.OK);

    @InjectMocks
    private ControllerAdvice controllerAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void requestExceptionHandler() {
        ResponseEntity<MessageDto> response = controllerAdvice.requestExceptionHandler(requestErrors);
        assertEquals(HttpStatus.OK ,response.getStatusCode());
    }

    @Test
    void communicationError() {
        ResponseEntity<MessageDto> response = controllerAdvice.communicationError();
        assertEquals("I-001", response.getBody().getCode());
    }

    @Test
    void aunthenticationError() {
        ResponseEntity<MessageDto> response = controllerAdvice.aunthenticationError();
        assertEquals("A-001", response.getBody().getCode());
    }

    @Test
    void tokenError() {
        ResponseEntity<MessageDto> response = controllerAdvice.tokenError();
        assertEquals("T-001", response.getBody().getCode());
    }
}