FROM openjdk:17-alpine
# Copy the target/student-service-0.0.1-SNAPSHOT.jar file to the container
COPY /target/student-service-0.0.1-SNAPSHOT.jar ./app.jar
# Set the entrypoint to java and the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]