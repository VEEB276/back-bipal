# Instrucciones para configurar el CI/CD de BIPAL Backend

Este documento proporciona instrucciones sobre cómo configurar el pipeline de CI/CD para el proyecto back-bipal.

## Configuración de Secretos en GitHub

Para que el workflow funcione correctamente, debes configurar los siguientes secretos en la configuración del repositorio en GitHub:

1. Ve a tu repositorio en GitHub
2. Navega a Settings > Secrets and variables > Actions
3. Agrega los siguientes secretos:

- `SERVER_HOST`: La dirección IP o hostname de tu servidor
- `SERVER_USERNAME`: El nombre de usuario para acceder por SSH
- `SERVER_SSH_KEY`: La clave SSH privada para acceder al servidor

## Configuración del Servidor

En el servidor de destino, asegúrate de tener instalado Docker:

```bash
sudo apt update
sudo apt install -y docker.io
sudo systemctl enable docker
sudo systemctl start docker
```

Además, crea un archivo de variables de entorno para el despliegue:

```bash
cat > /home/tu_usuario/.env << EOF
DB_URL=jdbc:mysql://hostname:3306/bipal?useSSL=false&serverTimezone=America/Bogota
DB_USERNAME=usuario_db
DB_PASSWORD=password_db
EOF
```

Asegúrate de que la carpeta de destino exista:

```bash
sudo mkdir -p ~/bipal-deploy
sudo mkdir -p /var/log/bipal
sudo chown -R tu_usuario:tu_usuario ~/bipal-deploy /var/log/bipal
```

## Funcionamiento del Workflow

Este workflow se activará en los siguientes casos:

1. Cuando se haga push directamente a la rama `pipeline`
2. Cuando se apruebe y fusione un Pull Request a la rama `develop`

El proceso realizará las siguientes acciones:
- Compilar el proyecto con Maven
- Ejecutar las pruebas
- Construir una imagen Docker
- Transferir la imagen y el script de despliegue al servidor
- Ejecutar el script de despliegue para actualizar la aplicación
