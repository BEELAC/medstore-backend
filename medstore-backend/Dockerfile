# lightweight java image
FROM openjdk:21-jdk-slim

# working directory
WORKDIR /app

# copy built jar into container
COPY target/medstore-backend-0.0.1-SNAPSHOT.jar app.jar

# expose Spring Boot port
EXPOSE 8081

# run the application
ENTRYPOINT ["java", "-jar", "app.jar"]