# Utiliser une image de base OpenJDK 17 slim
FROM openjdk:17-slim

# Exposer le port 8082 pour l'application
EXPOSE 8082

# Mettre à jour les paquets et installer curl
RUN apt-get update && apt-get install -y curl

# Télécharger le fichier JAR depuis le dépôt distant Nexus
RUN curl -o /tp-foyer-5.0.1.jar -L "http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.1/tp-foyer-5.0.1.jar"

# Définir le point d'entrée pour exécuter l'application Java
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.1.jar"]
