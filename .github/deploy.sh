#!/bin/bash

# Script de despliegue para la aplicación bipal-backend

# Detener y eliminar el contenedor anterior si existe
echo "Deteniendo contenedor anterior si existe..."
docker stop bipal-backend || true
docker rm bipal-backend || true

# Cargar la nueva imagen
echo "Cargando nueva imagen Docker..."
docker load < /tmp/bipal-deploy/docker-image/bipal-backend.tar

# Iniciar el nuevo contenedor
echo "Iniciando nuevo contenedor..."
docker run -d \
  --name bipal-backend \
  --restart always \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=${DB_URL} \
  -e SPRING_DATASOURCE_USERNAME=${DB_USERNAME} \
  -e SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD} \
  -e JWT_SECRET=${JWT_SECRET} \
  -e SPRING_PROFILES_ACTIVE=prod \
  -v /var/log/bipal:/logs \
  bipal-backend:latest

echo "Despliegue completado con éxito!"
