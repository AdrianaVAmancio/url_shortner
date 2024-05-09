#FROM openjdk:17-jdk-alpine
#
#ARG JAR_FILE=target/Url-Shortener-0.0.1-SNAPSHOT.jar
#
#COPY ${JAR_FILE} app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.9.6 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17.0.11_9-jre-focal
COPY --from=builder /app/target/Url-Shortener-0.0.1-SNAPSHOT.jar-0.0.2-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]