# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build files
COPY build/libs/FlightFlex-1.0-SNAPSHOT.jar /app/backend.jar

# Expose the backend port
EXPOSE 8080

# Command to run the Java app
CMD ["java", "-jar", "/app/backend.jar"]
