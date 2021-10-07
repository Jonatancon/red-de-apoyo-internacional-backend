package com.pragma.reddeapoyointernacionalbackend.api.resources.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CodificadorUtilTest {

    @InjectMocks
    CodificadorUtil codificadorUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void encript() throws Exception {
        assertNotNull(codificadorUtil.encript("21"));
    }

    @Test
    void decrypt() throws Exception {
        assertEquals("21", codificadorUtil.decrypt("caiqBF76ZOPfe7yuCxbQIw=="));
    }
}