FROM openjdk:11-jdk-slim

# Make port 8081 available to the world outside this container
EXPOSE 8081

ADD target/demo-qr-code-0.0.1-SNAPSHOT.jar demo-qr-code-0.0.1-SNAPSHOT.jar


# Run the jar file
ENTRYPOINT ["java","-jar","demo-qr-code-0.0.1-SNAPSHOT.jar"]