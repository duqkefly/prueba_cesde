# Gestión de Cursos - Cesde

Aplicación para registrar los cursos de Cesde y los docentes que los dictan. El back está en Spring Boot, la base de datos en MariaDB y el front es HTML con Bootstrap y JavaScript.

## Base de datos

Primero se corre el script que crea la base `cesde` con sus tablas:

```bash
mysql -u root -p < database/cesde.sql
```

El backend se conecta con el usuario `duqkefly` y contraseña `manutd10` en el puerto 3306. Si ese usuario no existe hay que crearlo:

```sql
CREATE USER 'duqkefly'@'localhost' IDENTIFIED BY 'manutd10';
GRANT ALL PRIVILEGES ON cesde.* TO 'duqkefly'@'localhost';
FLUSH PRIVILEGES;
```

(o cambiar el usuario y la clave en `backend/src/main/resources/application.properties`)

Si quieren arrancar con algunos datos para probar:

```sql
INSERT INTO docentes (nombre, documento, correo) VALUES
('Carlos Ramirez', '1036654321', 'carlos.ramirez@cesde.edu.co'),
('Laura Gomez', '1017223344', 'laura.gomez@cesde.edu.co');

INSERT INTO cursos (nombre, descripcion, duracion_semanas, precio, fecha_inicio, docente_id) VALUES
('Programacion en Java', 'Fundamentos de programacion orientada a objetos con Java', 12, 850000.00, '2026-10-13 18:00:00', 1),
('Bases de datos', 'Modelado entidad relacion y consultas SQL con MySQL', 8, 600000.00, '2026-10-20 18:00:00', 1),
('Desarrollo web', 'Maquetacion con HTML, CSS, Bootstrap y JavaScript', 10, 700000.00, '2026-11-03 08:00:00', 2);
```

## Correr el backend

Necesita Java 21 o más. No hace falta tener Maven instalado porque viene el wrapper.

```bash
cd backend
./mvnw spring-boot:run
```

En Windows: `mvnw.cmd spring-boot:run`

Queda en `http://localhost:8080`.

## Usarlo en el navegador

Con el backend corriendo se abre `frontend/index.html` en el navegador (doble clic sirve).

En la pestaña **Docentes** se registran los docentes y en **Cursos** se crean los cursos escogiendo el docente de la lista. Hay que crear al menos un docente antes de poder crear un curso.
