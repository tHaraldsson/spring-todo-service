FROM openjdk:21-jdk-slim
WORKDIR /app

# Kopiera allt källmaterial
COPY . .

# Gör gradlew körbar
RUN chmod +x gradlew

# Bygg JAR-fil
RUN ./gradlew clean build -x test

# Kör JAR-filen
CMD ["java", "-jar", "spring-todo-service/build/libs/spring-todo-service-0.0.1-SNAPSHOT.jar"]
