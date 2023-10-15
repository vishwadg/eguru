FROM maven:latest as builder
# Create a working directory for the build
WORKDIR build
# Copy the source code into the working directory
COPY . .
# Build the project
RUN mvn clean install -DskipTests


FROM openjdk:latest as deployer
# Get the build folder as an argument
ARG build_folder
# Create a working directory for the application
WORKDIR app
# Print the build folder
RUN echo $build_folder
# Copy the built project into the application working directory
COPY --from=builder build/$build_folder/target/*.jar app.jar
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]