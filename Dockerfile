FROM openjdk:22-jdk

WORKDIR /app

COPY target/docker-template.jar docker-template.jar

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java","-jar","docker-template.jar"]

EXPOSE 8080