package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter;

import com.appturismo.show.backend.domain.model.User;
import com.appturismo.show.backend.domain.model.gateway.UserGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.commons.DatabaseErrorMessages;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.exception.InfrastructureDatabaseException;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper.UserModelMapper;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories.UsuarioRepository;
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

    @Override
    public User createUser(User user) {
        return null;
    }
}