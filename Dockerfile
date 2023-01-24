FROM docker.io/openjdk:18-alpine
WORKDIR /app
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run