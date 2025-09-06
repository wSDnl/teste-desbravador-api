FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR e o .env
ARG JAR_FILE=api-botconcursos-modulo-usuarios-1.jar
COPY ${JAR_FILE} app.jar
COPY .env /app/.env

# Expõe a porta
EXPOSE 8085

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


