-- ============================================================
--   TURISMO EN CICLAS - BASE DE DATOS POSTGRES
--   Script completo con PK, FK, índices y constraints
-- ============================================================

-- =============================
-- 1. Crear base de datos
-- =============================
CREATE DATABASE turismo_db
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'es_CO.utf8'
    LC_CTYPE = 'es_CO.utf8'
    TEMPLATE = template0;

-- Conectar
-- \c turismo_db;

-- =============================
-- 2. Extension UUID (opcional)
-- =============================
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ============================================================
-- 3. TABLAS PRINCIPALES
-- ============================================================

-- =============================
-- Tabla: tipo_documento
-- =============================
CREATE TABLE tipo_documento (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    descripcion VARCHAR(200) NOT NULL
);

COMMENT ON TABLE tipo_documento IS 'Tipos de identificación oficiales para Colombia';

-- =============================
-- Tabla: pais
-- =============================

CREATE TABLE pais (
    id SERIAL PRIMARY KEY,
    iso2 VARCHAR(2) NOT NULL UNIQUE,          -- "CO"
    iso3 VARCHAR(3) NOT NULL UNIQUE,          -- "COL"
    nombre VARCHAR(150) NOT NULL,             -- "Colombia"
    prefijo_telefono VARCHAR(10) NOT NULL     -- "+57"
);

CREATE INDEX idx_pais_iso2 ON pais(iso2);
CREATE INDEX idx_pais_nombre ON pais(nombre);




-- =============================
-- Tabla: persona
-- =============================
CREATE TABLE persona (
    id SERIAL PRIMARY KEY,

    -- Datos básicos
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,

    -- Identificación
    id_tipo_documento INT NOT NULL,
    numero_documento VARCHAR(30) NOT NULL,

    -- Datos requeridos por seguros / validación
    fecha_nacimiento DATE NOT NULL,
    ciudad_nacimiento VARCHAR(100) NOT NULL,

    fecha_expedicion_documento DATE NOT NULL,
    ciudad_expedicion VARCHAR(100) NOT NULL,

    -- Domicilio actual
    direccion VARCHAR(250) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,

    -- País de residencia (prefijo vía FK)
    id_pais INT NOT NULL,

    -- Contacto
    email VARCHAR(150) NOT NULL,
    telefono_personal VARCHAR(20),
    telefono_whatsapp VARCHAR(20),

    fecha_creacion TIMESTAMP DEFAULT NOW(),

    -- Reglas de unicidad
    UNIQUE (id_tipo_documento, numero_documento),
    UNIQUE (email),

    -- Foráneas
    CONSTRAINT fk_persona_tipo_documento
        FOREIGN KEY (id_tipo_documento) REFERENCES tipo_documento(id),

    CONSTRAINT fk_persona_pais
        FOREIGN KEY (id_pais) REFERENCES pais(id)
);

-- Índices recomendados
CREATE INDEX idx_persona_documento
    ON persona(id_tipo_documento, numero_documento);

CREATE INDEX idx_persona_email
    ON persona(email);

CREATE INDEX idx_persona_pais
    ON persona(id_pais);

CREATE INDEX idx_persona_telefono_whatsapp
    ON persona(telefono_whatsapp);

-- =============================
-- Tabla: rol
-- =============================
CREATE TABLE rol (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

-- Roles por defecto
INSERT INTO rol (nombre, descripcion) VALUES
('ADMIN', 'Administrador general del sistema'),
('CLIENTE', 'Usuario cliente que compra y usa planes');


-- =============================
-- Tabla: usuario
-- =============================
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,

    id_persona INT NOT NULL,
    id_rol INT NOT NULL,

    username VARCHAR(150) NOT NULL,
    password_hash TEXT NOT NULL,

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    foto_base64 TEXT,  -- foto de carnet o perfil

    fecha_creacion TIMESTAMP DEFAULT NOW(),

    -- Unicidad
    UNIQUE (id_persona),
    UNIQUE (username),

    -- Llaves foráneas
    CONSTRAINT fk_usuario_persona
        FOREIGN KEY (id_persona) REFERENCES persona(id),

    CONSTRAINT fk_usuario_rol
        FOREIGN KEY (id_rol) REFERENCES rol(id)
);

-- Índices adicionales
CREATE INDEX idx_usuario_persona ON usuario(id_persona);
CREATE INDEX idx_usuario_rol ON usuario(id_rol);


-- =============================
-- Tabla: plan_turismo
-- =============================
CREATE TABLE plan_turismo (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(12,2) NOT NULL CHECK (precio >= 0),
    fecha_inicio DATE,
    fecha_fin DATE,
    activo BOOLEAN DEFAULT TRUE,

    fecha_creacion TIMESTAMP DEFAULT NOW(),
    fecha_modificacion TIMESTAMP NULL
);


-- Índices
CREATE INDEX idx_plan_precio ON plan_turismo(precio);
CREATE INDEX idx_plan_activo ON plan_turismo(activo);


-- =============================
-- Tabla: plan_imagen
-- =============================
CREATE TABLE plan_imagen (
    id SERIAL PRIMARY KEY,
    id_plan INT NOT NULL,
    imagen_base64 TEXT NOT NULL,
    orden INT DEFAULT 1,
	fecha_creacion TIMESTAMP DEFAULT NOW()
    CONSTRAINT fk_plan_imagen_plan
        FOREIGN KEY (id_plan) REFERENCES plan_turismo(id)
);

-- Índice
CREATE INDEX idx_plan_imagen_plan ON plan_imagen(id_plan);


-- =============================
-- Tabla: pre_registro
-- =============================

CREATE TYPE estado_preregistro AS ENUM (
    'PENDIENTE',
    'LINK_ENVIADO',
    'CANCELADO'
);


CREATE TABLE pre_registro (
    id SERIAL PRIMARY KEY,
    id_persona INT NOT NULL,
    id_plan INT NOT NULL,

    estado estado_preregistro NOT NULL DEFAULT 'PENDIENTE',

    fecha_registro TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_pre_registro_persona
        FOREIGN KEY (id_persona) REFERENCES persona(id),

    CONSTRAINT fk_pre_registro_plan
        FOREIGN KEY (id_plan) REFERENCES plan_turismo(id)
);

-- Índices
CREATE INDEX idx_pre_registro_persona ON pre_registro(id_persona);
CREATE INDEX idx_pre_registro_plan ON pre_registro(id_plan);
CREATE INDEX idx_pre_registro_estado ON pre_registro(estado);



-- =============================
-- Tabla: pago
-- =============================

CREATE TYPE estado_pago AS ENUM (
    'PENDIENTE',
    'PAGADO',
    'FALLIDO'
);


CREATE TABLE pago (
    id SERIAL PRIMARY KEY,
    id_persona INT NOT NULL,
    id_plan INT NOT NULL,
    referencia_externa VARCHAR(200),

    status estado_pago NOT NULL DEFAULT 'PENDIENTE',

    fecha_pago TIMESTAMP,
    fecha_creacion TIMESTAMP DEFAULT NOW(),
    fecha_modificacion TIMESTAMP NULL,

    CONSTRAINT fk_pago_persona
        FOREIGN KEY (id_persona) REFERENCES persona(id),

    CONSTRAINT fk_pago_plan
        FOREIGN KEY (id_plan) REFERENCES plan_turismo(id)
);

-- Índices optimizados
CREATE INDEX idx_pago_persona ON pago(id_persona);
CREATE INDEX idx_pago_plan ON pago(id_plan);
CREATE INDEX idx_pago_status ON pago(status);
CREATE INDEX idx_pago_referencia ON pago(referencia_externa);


-- =============================
-- Tabla: plan_cliente
-- =============================
CREATE TABLE plan_cliente (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_plan INT NOT NULL,
    fecha_asignacion TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_plan_cliente_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id),

    CONSTRAINT fk_plan_cliente_plan
        FOREIGN KEY (id_plan) REFERENCES plan_turismo(id)
);

-- Índices
CREATE INDEX idx_plan_cliente_usuario ON plan_cliente(id_usuario);
CREATE INDEX idx_plan_cliente_plan ON plan_cliente(id_plan);


-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================
