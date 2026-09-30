# Gestión de Cursos - Cesde

Aplicación web para administrar los cursos y docentes de Cesde.

## Base de datos

Uso MariaDB (también funciona con MySQL). El script con la base de datos y las tablas está en `database/cesde.sql`:

```bash
mysql -u root -p < database/cesde.sql
```

El proyecto se conecta con estos datos:

- Base de datos: `cesde`
- Usuario: `duqkefly`
- Contraseña: `manutd10`
- Puerto: `3306`

Si el usuario no existe en su equipo se puede crear así:

```sql
CREATE USER 'duqkefly'@'localhost' IDENTIFIED BY 'manutd10';
GRANT ALL PRIVILEGES ON cesde.* TO 'duqkefly'@'localhost';
FLUSH PRIVILEGES;
```

Si prefiere usar otro usuario se cambia en `backend/src/main/resources/application.properties`.

### Datos de prueba

Para probar se pueden insertar unos docentes y cursos:

```sql
INSERT INTO docentes (nombre, documento, correo) VALUES
('Carlos Ramirez', '1036654321', 'carlos.ramirez@cesde.edu.co'),
('Laura Gomez', '1017223344', 'laura.gomez@cesde.edu.co');

INSERT INTO cursos (nombre, descripcion, duracion_semanas, precio, fecha_inicio, docente_id) VALUES
('Programacion en Java', 'Fundamentos de programacion orientada a objetos con Java', 12, 850000.00, '2026-10-13 18:00:00', 1),
('Bases de datos', 'Modelado entidad relacion y consultas SQL con MySQL', 8, 600000.00, '2026-10-20 18:00:00', 1),
('Desarrollo web', 'Maquetacion con HTML, CSS, Bootstrap y JavaScript', 10, 700000.00, '2026-11-03 08:00:00', 2);
```

Y para ver los cursos con su docente:

```sql
SELECT c.id, c.nombre, c.precio, c.fecha_inicio, d.nombre AS docente
FROM cursos c
JOIN docentes d ON d.id = c.docente_id;
```

## Backend

Está hecho con Spring Boot. Se necesita Java 21 o superior, Maven no hace falta porque el proyecto trae el wrapper.

```bash
cd backend
./mvnw spring-boot:run
```

En Windows es `mvnw.cmd spring-boot:run`.

La API queda corriendo en `http://localhost:8080`.
