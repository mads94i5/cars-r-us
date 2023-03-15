# Base image
FROM ubuntu:22.04

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

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw package

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/car-0.0.1-SNAPSHOT.jar"]