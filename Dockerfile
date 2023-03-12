FROM openjdk:17-jdk-slim-bullseye
FROM mysql:8.0.32
# ENV JAVA_OPTS = "-Xmx256m -Xms128"

WORKDIR /app
RUN apt-get update && \
    apt-get install -y maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

#Next two lines ONLY for Windows users
RUN apt-get update && apt-get install dos2unix
RUN dos2unix mvnw

COPY src ./src
RUN chmod +x mvnw && ./mvnw dependency:resolve
RUN mvn clean package
EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]