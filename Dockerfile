# Base image
FROM ubuntu:22.04

# Install curl and other utilities
RUN apk add --no-cache curl tar bash

# Define arguments
ARG JDBC_DATABASE
ARG JDBC_USERNAME
ARG JDBC_PASSWORD

# Set environment variables
ENV JDBC_DATABASE $JDBC_DATABASE
ENV JDBC_USERNAME $JDBC_USERNAME
ENV JDBC_PASSWORD $JDBC_PASSWORD

# Install OpenJDK 17 slim
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk-headless && \
    rm -rf /var/lib/apt/lists/*

# Download and extract Maven
ENV MAVEN_VERSION=3.8.3
ENV MAVEN_HOME=/usr/lib/mvn
RUN mkdir -p ${MAVEN_HOME} \
    && curl -fsSL https://downloads.apache.org/maven/maven-3/$%7BMAVEN_VERSION%7D/binaries/apache-maven-$%7BMAVEN_VERSION%7D-bin.tar.gz \
    | tar -xzC ${MAVEN_HOME} --strip-components=1 \
    && ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

# Set Maven environment variables
ENV PATH=${MAVEN_HOME}/bin:${PATH} \
    MAVEN_OPTS="-Xms256m -Xmx512m"

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw package

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/car-0.0.1-SNAPSHOT.jar"]