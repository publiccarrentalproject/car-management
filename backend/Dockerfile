FROM adoptopenjdk/openjdk11:alpine-slim

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backend.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/backend.jar"]    