package com.pragma.reddeapoyointernacionalbackend.http_errors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class RequestErrorsTest {

    RequestErrors error = new RequestErrors("Hola", "A-11", HttpStatus.OK);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCode() {
        assertEquals("A-11", error.getCode());
    }

    @Test
    void getHttpStatus() {
        assertEquals(HttpStatus.OK, error.getHttpStatus());
    }
}