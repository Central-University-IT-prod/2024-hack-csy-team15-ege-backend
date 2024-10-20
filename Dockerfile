FROM eclipse-temurin:21-jdk-alpine
LABEL authors="{{sensitive data}}"
MAINTAINER {{sensitive data}}
WORKDIR /app
COPY target/school-0.0.1-SNAPSHOT.jar /app/server-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/app/server-0.0.1.jar"]