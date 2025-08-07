FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el archivo JAR generado por Maven
COPY target/*.jar app.jar

# Variables de entorno que se pueden sobrescribir al ejecutar el contenedor
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/bipal?useSSL=false&serverTimezone=America/Bogota
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=password
ENV JWT_SECRET=rsi
ENV ENABLE_PREFLIGHT=false

# Puerto que expondrá la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
