FROM openjdk:21-jdk-slim AS build

WORKDIR /app

# Kopiera Gradle wrapper och build-script
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY spring-todo-service ./spring-todo-service

# Ge körbarhetsrättigheter till wrapper
RUN chmod +x ./gradlew

# Bygg JAR
RUN ./gradlew -p spring-todo-service build

# Starta en ren runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/spring-todo-service/build/libs/spring-todo-service-0.0.1-SNAPSHOT.jar /app/spring-todo-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/spring-todo-service.jar"]
