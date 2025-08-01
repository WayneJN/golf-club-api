FROM openjdk:17-jdk-slim
LABEL authors="wayne"
WORKDIR /app
COPY target/golf-club-api-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

