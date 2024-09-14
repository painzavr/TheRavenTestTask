FROM openjdk:22-jre-slim

WORKDIR /app

COPY target/Raven.jar /app/Raven.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/Raven.jar"]
