package com.turismo.show.backend.infrastructure.adapters.jpapostgresql;

import com.turismo.show.backend.domain.gateway.UserGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresql.commons.DatabaseErrorMessages;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresql.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresql.mapper.UserModelMapper;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresql.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
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
        }catch (InfrastructureDatabaseException e) {
            throw new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, e);
        } catch (Exception e) {
            throw new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, e);
        }
    }
}
