FROM openjdk:21-jdk-slim
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY spring-todo-service ./spring-todo-service
RUN ./gradlew build
COPY spring-todo-service/build/libs/spring-todo-service-0.0.1-SNAPSHOT.jar /app/spring-todo-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/spring-todo-service.jar"]
