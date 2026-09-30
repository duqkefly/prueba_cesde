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

## Backend

Está hecho con Spring Boot. Se necesita Java 21 o superior, Maven no hace falta porque el proyecto trae el wrapper.

```bash
cd backend
./mvnw spring-boot:run
```

En Windows es `mvnw.cmd spring-boot:run`.

La API queda corriendo en `http://localhost:8080`.
