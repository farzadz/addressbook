# syntax = docker/dockerfile:1.0-experimental
FROM maven:3.6.3-jdk-11 as builder

WORKDIR /usr/src/app

COPY [".", "./"]

RUN mvn -Dmaven.test.skip=true clean package

FROM openjdk:11.0.8-slim

WORKDIR /app

COPY --from=builder ["/usr/src/app/target/addressbook-*-SNAPSHOT.jar", "/app/addressbook.jar"]

ENTRYPOINT ["java", "-jar", "/app/addressbook.jar"]


