# Step 1: Use a lightweight base image for downloading the JAR
FROM eclipse-temurin:21-jre AS downloader

# Define the Nexus repository and artifact details
ARG NEXUS_URL=http://localhost:8081/repository/maven-releases/
ARG GROUP_ID=tn.esprit
ARG ARTIFACT_ID=tp-foyer
ARG VERSION=5.0.0-RELEASE

# Convert groupId to the path format used in Nexus (replace dots with slashes) and download the JAR
RUN mkdir -p /app && \
    ARTIFACT_PATH=$(echo $GROUP_ID | tr '.' '/') && \
    curl -o /app/application.jar "$NEXUS_URL$ARTIFACT_PATH/$ARTIFACT_ID/$VERSION/$ARTIFACT_ID-$VERSION.jar"

# Step 2: Use a Java 21 runtime image to run the application
FROM eclipse-temurin:21-jre

# Copy the downloaded JAR
COPY --from=downloader /app/application.jar /app/application.jar

# Expose the port and set the entrypoint
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app/application.jar"]
