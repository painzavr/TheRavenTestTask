FROM openjdk:17-oracle

WORKDIR /app

COPY target/Raven.jar /app/Raven.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/Raven.jar"]
