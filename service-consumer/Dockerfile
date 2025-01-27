FROM maven:3.9.9-amazoncorretto-21-alpine AS build
COPY src /service-consumer-build/src
COPY pom.xml /service-consumer-build
WORKDIR /service-consumer-build
RUN mvn -DskipTests clean package

FROM openjdk:21
COPY --from=build /service-consumer-build/target/*.jar /service-consumer-image/app.jar
WORKDIR /service-consumer-image
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]