FROM adoptopenjdk/openjdk11:alpine-slim

EXPOSE 8585

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backend.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/backend.jar"]    