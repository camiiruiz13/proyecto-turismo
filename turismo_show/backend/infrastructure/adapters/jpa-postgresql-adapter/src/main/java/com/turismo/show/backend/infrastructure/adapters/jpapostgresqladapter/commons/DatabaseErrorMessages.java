package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseErrorMessages {

    // ====== ERRORES DE CONEXIÓN ======
    public static final String CONNECTION_FAILED =
            "No se pudo establecer conexión con la base de datos.";

    public static final String CONNECTION_TIMEOUT =
            "La conexión con la base de datos excedió el tiempo máximo permitido.";

    public static final String CONNECTION_CLOSED =
            "La conexión con la base de datos se cerró inesperadamente.";


    // ====== ERRORES DE TRANSACCIÓN ======
    public static final String TRANSACTION_FAILED =
            "Ocurrió un error durante la transacción con la base de datos.";

    public static final String TRANSACTION_ROLLBACK =
            "La transacción fue revertida debido a un error en la base de datos.";


    // ====== ERRORES DE CONSULTAS / CRUD ======

    public static final String QUERY_EXECUTION_FAILED =
            "Error al ejecutar la consulta en la base de datos.";

    public static final String UPDATE_FAILED =
            "No se pudo actualizar el registro en la base de datos.";

    public static final String INSERT_FAILED =
            "No se pudo insertar el registro en la base de datos.";

    public static final String DELETE_FAILED =
            "No se pudo eliminar el registro de la base de datos.";


    // ====== ERRORES DE INTEGRIDAD ======
    public static final String DUPLICATE_KEY =
            "Violación de clave única: el recurso ya existe en la base de datos.";

    public static final String FOREIGN_KEY_VIOLATION =
            "Violación de llave foránea: existen dependencias relacionadas.";

    public static final String DATA_INTEGRITY_VIOLATION =
            "Violación de integridad de datos: datos inválidos o inconsistentes.";


    // ====== ERRORES DE BLOQUEO / DEADLOCK ======
    public static final String DEADLOCK_DETECTED =
            "Se detectó un bloqueo en la base de datos (deadlock).";

    public static final String LOCK_TIMEOUT =
            "Expiró el tiempo de espera intentando adquirir un bloqueo en la base de datos.";
}
