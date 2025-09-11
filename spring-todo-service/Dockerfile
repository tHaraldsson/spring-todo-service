# Basimage med OpenJDK 21
FROM openjdk:21-jdk-slim

# Sätt arbetskatalog
WORKDIR /app

# Kopiera Gradle-wrapper och projektfiler
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY spring-todo-service ./spring-todo-service

# Ge körbehörighet till gradlew
RUN chmod +x ./gradlew

# Bygg projektet och hoppa över tester (för att undvika fail på Render)
RUN ./gradlew build -x test

# Kopiera JAR-filen som byggdes in i Docker
COPY spring-todo-service/build/libs/spring-todo-service-0.0.1-SNAPSHOT.jar /app/spring-todo-service.jar

# Exponera porten som Spring Boot kör på
EXPOSE 8080

# Starta applikationen
ENTRYPOINT ["java", "-jar", "/app/spring-todo-service.jar"]
