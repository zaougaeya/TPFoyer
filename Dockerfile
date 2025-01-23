FROM openjdk:17-slim

EXPOSE 8082

#Installer curl
RUN apt-get update && apt-get install -y curl

#Télécharger le JAR depuis le dépôt distant
RUN curl -o tp-foyer-5.0.0.jar -L "http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar"
#ADD target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar
#Définir le point d'entrée pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]