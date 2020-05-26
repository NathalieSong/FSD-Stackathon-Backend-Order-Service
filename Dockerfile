FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 9003

ENTRYPOINT ["java","-jar", "/app/order-service-0.0.1-SNAPSHOT.jar"]