FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/spring-todo-service-0.0.1-SNAPSHOT.jar /app/spring-todo-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/spring-todo-service.jar"]