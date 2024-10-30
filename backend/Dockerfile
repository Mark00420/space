FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]

