FROM openjdk:20-jdk-bullseye

WORKDIR /app
RUN apt-get update && apt-get install dos2unix
COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml pom.xml

COPY src ./src

RUN dos2unix mvnw

CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=prod"]