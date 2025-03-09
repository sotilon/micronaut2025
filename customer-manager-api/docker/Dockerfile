# Etapa 1: Construcción de la aplicación
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiar archivos de configuración y dependencias
COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

# Dar permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# Construir la aplicación
RUN ./gradlew build --no-daemon

# Etapa 2: Imagen final con la aplicación
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar el JAR generado en la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto en el que corre Micronaut (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]