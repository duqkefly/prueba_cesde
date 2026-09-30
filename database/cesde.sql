CREATE DATABASE IF NOT EXISTS cesde CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cesde;

CREATE TABLE docentes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  documento VARCHAR(20) NOT NULL UNIQUE,
  correo VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE cursos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(500),
  duracion_semanas INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  fecha_inicio DATETIME NOT NULL,
  docente_id BIGINT NOT NULL,
  CONSTRAINT fk_curso_docente FOREIGN KEY (docente_id) REFERENCES docentes(id)
);
