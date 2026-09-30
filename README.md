# Gestión de Cursos - Cesde

Aplicación web para administrar los cursos y docentes de Cesde.

## Base de datos

Uso MariaDB (también funciona con MySQL). Primero hay que crear la base de datos:

```sql
CREATE DATABASE cesde CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Las tablas no hace falta crearlas, se generan solas cuando se levanta el backend.

El proyecto se conecta con estos datos:

- Usuario: `root`
- Contraseña: vacía
- Puerto: `3306`

Si en su equipo el usuario root tiene contraseña, se puede cambiar en `backend/src/main/resources/application.properties` o pasarla al ejecutar (ver abajo).

## Backend

Está hecho con Spring Boot. Se necesita Java 21 o superior, Maven no hace falta porque el proyecto trae el wrapper.

```bash
cd backend
./mvnw spring-boot:run
```

En Windows es `mvnw.cmd spring-boot:run`.

Si root tiene contraseña:

```bash
DB_PASSWORD=su_clave ./mvnw spring-boot:run
```

La API queda corriendo en `http://localhost:8080`.
