# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the target directory into the container
COPY target/*.jar app.jar

# Expose the port that the application runs on
EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
