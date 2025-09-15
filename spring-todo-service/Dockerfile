# Basimage med JDK 21
FROM openjdk:21-jdk-slim

# Arbeta i /app
WORKDIR /app

# Kopiera allt från subfoldern spring-todo-service in i /app
COPY spring-todo-service/ .

# Gör gradlew körbar
RUN chmod +x gradlew

# Bygg projektet utan tester
RUN ./gradlew clean build -x test

# Exponera porten
EXPOSE 8080

# Starta appen
CMD ["java", "-jar", "build/libs/spring-todo-service-0.0.1-SNAPSHOT.jar"]
