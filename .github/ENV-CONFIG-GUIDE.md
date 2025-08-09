# Guía para configurar las Variables de Entorno en el Servidor

Este documento explica cómo configurar las variables de entorno necesarias para ejecutar la aplicación BIPAL Backend en tu servidor utilizando Docker Compose.

## Archivo .env

Docker Compose puede leer automáticamente un archivo llamado `.env` ubicado en el mismo directorio que el archivo `docker-compose.yml`. Este archivo contiene las variables de entorno que serán utilizadas por los servicios definidos en Docker Compose.

## Configuración inicial

Cuando el script de despliegue se ejecuta por primera vez, buscará un archivo `.env` en el directorio de despliegue. Si no existe, intentará crear uno basado en la plantilla `.env.example`.

## Opciones para configurar el archivo .env

### Opción 1: Dejar que el script lo configure interactivamente

Al ejecutar el script de despliegue, si no existe un archivo `.env`, el script te pedirá ingresar:
- La URL de la base de datos
- El nombre de usuario de la base de datos
- La contraseña de la base de datos

### Opción 2: Crear manualmente el archivo .env antes del despliegue

Puedes crear manualmente el archivo `.env` en el servidor:

```bash
# Conectarse al servidor
ssh tu_usuario@tu_servidor

# Crear el directorio si no existe
mkdir -p ~/bipal-deploy

# Crear o editar el archivo .env
nano ~/bipal-deploy/.env
```

Contenido ejemplo del archivo `.env`:

```
# Configuración de la base de datos
SPRING_DATASOURCE_URL=jdbc:mysql://tu-servidor-db:3306/bipal?useSSL=false&serverTimezone=America/Bogota
SPRING_DATASOURCE_USERNAME=tu_usuario
SPRING_DATASOURCE_PASSWORD=tu_contraseña
```

### Opción 3: Usar variables de entorno del sistema

También puedes exportar las variables en el sistema antes de ejecutar Docker Compose:

```bash
export SPRING_DATASOURCE_URL="jdbc:mysql://tu-servidor-db:3306/bipal?useSSL=false&serverTimezone=America/Bogota"
export SPRING_DATASOURCE_USERNAME="tu_usuario"
export SPRING_DATASOURCE_PASSWORD="tu_contraseña"

docker-compose up -d
```

## Variables disponibles

| Variable | Descripción | Valor por defecto |
|----------|-------------|------------------|
| SPRING_DATASOURCE_URL | URL completa de conexión a la base de datos | jdbc:mysql://localhost:3306/bipal?useSSL=false&serverTimezone=America/Bogota |
| SPRING_DATASOURCE_USERNAME | Usuario de la base de datos | root |
| SPRING_DATASOURCE_PASSWORD | Contraseña de la base de datos | password |

## Seguridad

- El archivo `.env` contiene información sensible como contraseñas. Asegúrate de que solo los usuarios autorizados puedan acceder a él.
- Recomendación: `chmod 600 .env` para establecer permisos restrictivos.
- Considera usar un sistema de gestión de secretos más seguro para entornos de producción.
