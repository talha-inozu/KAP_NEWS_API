# Use a base image with Java 17 installed
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file to the container
COPY target/kapnews.jar app.jar

# Expose the port on which the Spring Boot application will listen
EXPOSE 8080

# Specify the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]