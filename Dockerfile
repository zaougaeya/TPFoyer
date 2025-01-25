<<<<<<< HEAD
FROM openjdk:17-slim

EXPOSE 8082

#Installer curl
RUN apt-get update && apt-get install -y curl

#Télécharger le JAR depuis le dépôt distant
RUN curl -o tp-foyer-5.0.0.jar -L "http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar"
#ADD target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar
#Définir le point d'entrée pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]
=======
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
>>>>>>> 9d221bd1950b60106086a498aa1ebce782981bda
