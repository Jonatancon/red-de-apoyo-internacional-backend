package com.pragma.reddeapoyointernacionalbackend.configurations.jwt;

import com.pragma.reddeapoyointernacionalbackend.api.dtos.JwtDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    private final JwtProvider jwtProvider = new JwtProvider("Monogatari", 90000L);

    private final JwtProvider jwt = new JwtProvider("Monogatari", 1L);

    private final AuthenticationMock authentication = new AuthenticationMock();

    private String token;

    private String tokenIlegal = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.jYW04zLDHfR1v7xdrW3lCGZrMIsVe0vWCfVkN2DRns2c3MN-mcp_-RE6TN9umSBYoNV-mnb31wFf8iun3fB6aDS6m_OXAiURVEKrPFNGlR38JSHUtsFzqTOj-wFrJZN4RwvZnNGSMvK3wzzUriZqmiNLsG8lktlEn6KA4kYVaM61_NpmPHWAjGExWv7cjHYupcjMSmR8uMTwN5UuAwgW6FRstCJEfoxwb0WKiyoaSlDuIiHZJ0cyGhhEmmAPiCwtPAwGeaL1yZMcp0p82cpTQ5Qb-7CtRov3N4DcOHgWYk6LomPR5j5cCkePAz87duqyzSMpCB0mCOuE3CU2VMtGeQ";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        token = jwtProvider.generarToken(authentication);
    }

    @Test
    void generarToken() {
        assertNotNull(jwtProvider.generarToken(authentication));
    }

    @Test
    void getNombreUsuarioFromToken() {
        assertEquals("jrstark", jwtProvider.getNombreUsuarioFromToken(token));
    }

    @Test
    void validateTokenOk() {
        assertTrue(jwtProvider.validateToken(token));
    }

    @Test
    void validateTokenMalformed() {
        assertFalse(jwtProvider.validateToken("sdklfgsdighwoiefhhgjdflgh"));
    }

    @Test
    void validateTokenNotSupport() {
        assertFalse(jwtProvider.validateToken(tokenIlegal));
    }

    @Test
    void validateTokenExpired() {
        String tokenThree = jwt.generarToken(authentication);

        assertFalse(jwtProvider.validateToken(tokenThree));
    }

    @Test
    void refreshToken() throws ParseException {
        assertNotNull(jwtProvider.refreshToken(JwtDto.builder().token(token).build()));
    }

    @Test
    void refreshTokenExceptionTime() throws ParseException {
        String tokenTwo = jwt.generarToken(authentication);

        assertNotNull(jwt.refreshToken(JwtDto.builder().token(tokenTwo).build()));
    }
}