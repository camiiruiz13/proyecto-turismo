package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter;

import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.commons.DatabaseErrorMessages;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.UsuarioEntity;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.mapper.UserModelMapper;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UserModelMapper userModelMapper;

    @InjectMocks
    private UserAdapter userAdapter;

    private final String EMAIL = "user@test.com";

    @Test
    void testFindByCorreoReturnsUser() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        User expectedUser = new User();

        when(usuarioRepository.findByUsernameAndActivoTrue(EMAIL)).thenReturn(Optional.of(usuarioEntity));
        when(userModelMapper.toModel(usuarioEntity)).thenReturn(expectedUser);

        User result = userAdapter.findByCorreo(EMAIL);

        assertNotNull(result);
    }

    @Test
    void testFindByCorreoReturnsNullWhenNotFound() {
        when(usuarioRepository.findByUsernameAndActivoTrue(EMAIL)).thenReturn(Optional.empty());

        User result = userAdapter.findByCorreo(EMAIL);

        assertNull(result);
    }

    @Test
    void testFindByCorreoThrowsInfrastructureDatabaseException() {
        RuntimeException dbException = new RuntimeException("DB error");
        when(usuarioRepository.findByUsernameAndActivoTrue(EMAIL)).thenThrow(dbException);

        InfrastructureDatabaseException exception = assertThrows(
                InfrastructureDatabaseException.class,
                () -> userAdapter.findByCorreo(EMAIL)
        );

        assertEquals(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, exception.getMessage());
    }
}