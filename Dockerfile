# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set environment variable
ENV APP_HOME=/app
WORKDIR $APP_HOME

# Copy the jar file (make sure your jar is built first!)
COPY target/RegistryService-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8761

# Run the jar file (fixed .jar extension)
ENTRYPOINT ["java", "-jar", "app.jar"]
