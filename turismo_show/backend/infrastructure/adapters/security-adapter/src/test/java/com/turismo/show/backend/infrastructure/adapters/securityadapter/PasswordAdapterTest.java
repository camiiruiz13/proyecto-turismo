package com.turismo.show.backend.infrastructure.adapters.securityadapter;

import com.turismo.show.backend.infrastructure.adapters.securityadapter.exception.AutenticationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordAdapterTest {
    @Mock
    private  PasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordAdapter passwordAdapter;

    @Test
    void shouldReturnTrueWhenPasswordMatches() {
        String rawPassword = "1234";
        String hashedPassword = "$2a$10$hashDeEjemplo";


        when(passwordEncoder.matches(rawPassword, hashedPassword)).thenReturn(true);
        when(passwordEncoder.encode(rawPassword)).thenReturn("$2a$10$hashNuevo");

        Boolean result = passwordAdapter.esClaveValida(rawPassword, hashedPassword);

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        String rawPassword = "incorrecto";
        String hashedPassword = "$2a$10$hashDeEjemplo";

        when(passwordEncoder.matches(rawPassword, hashedPassword)).thenReturn(false);
        when(passwordEncoder.encode(rawPassword)).thenReturn("$2a$10$hashNuevo");

        AutenticationException exception = assertThrows(
                AutenticationException.class,
                () -> passwordAdapter.esClaveValida(rawPassword, hashedPassword)
        );


        verify(passwordEncoder, atLeastOnce()).matches(rawPassword, hashedPassword);
    }

}