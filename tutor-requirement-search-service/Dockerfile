FROM openjdk:17-alpine
# Copy the target jar file to the container
COPY /target/tutor-requirement-search-service-0.0.1-SNAPSHOT.jar ./app.jar
# Set the entrypoint to the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]