# ======== ETAPA 1: Construcción ========
FROM eclipse-temurin:21.0.3_9-jdk AS build
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY ./pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

# Dar permisos de ejecución a mvnw
RUN chmod +x mvnw

# Descargar dependencias para acelerar la construcción
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY ./src ./src

# Compilar el proyecto (sin ejecutar pruebas)
RUN ./mvnw clean package -DskipTests

# ======== ETAPA 2: Imagen final ligera ========
FROM eclipse-temurin:21.0.3_9-jre
WORKDIR /app

# Copiar solo el JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]