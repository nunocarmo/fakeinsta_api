FROM docker.io/openjdk:18-alpine
WORKDIR /app
COPY . .
CMD mvn spring-boot:run