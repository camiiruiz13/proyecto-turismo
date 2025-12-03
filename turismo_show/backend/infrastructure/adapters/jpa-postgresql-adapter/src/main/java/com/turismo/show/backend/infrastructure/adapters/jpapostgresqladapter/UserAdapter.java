package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter;

import com.turismo.show.backend.domain.gateway.UserGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.commons.DatabaseErrorMessages;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.mapper.UserModelMapper;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAdapter implements UserGateway {

    private final UsuarioRepository usuarioRepository;
    private final UserModelMapper userModelMapper;
    @Override
    @Transactional(readOnly = true)
    public User findByCorreo(String correo) {
        try {
            return usuarioRepository.findByUsernameAndActivoTrue(correo)
                    .map(userModelMapper::toModel)
                    .orElse(null);
        } catch (Exception e) {
            throw new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, e);
        }
    }
}
