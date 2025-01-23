# Étape 1 : Utiliser une image de base Java
FROM openjdk:17-jdk-alpine

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
COPY target/tp-foyer-*.jar app.jar

# Étape 4 : Exposer le port (8080 est le port par défaut pour Spring Boot)
EXPOSE 8080

# Étape 5 : Commande à exécuter lorsque le conteneur démarre
ENTRYPOINT ["java", "-jar", "app.jar"]
