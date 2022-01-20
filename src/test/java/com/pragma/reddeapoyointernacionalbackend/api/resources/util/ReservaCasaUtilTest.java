package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ReservaCasaUtilTest {

    @InjectMocks
    private ReservaCasaUtil reservaCasaUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void controlDate() {
        String fechaI = "05/01/2022";
        String fechaF = "10/01/2022";

        assertFalse(reservaCasaUtil.controlDate(fechaI, fechaF));
    }
}