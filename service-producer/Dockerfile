FROM maven:3.9.9-amazoncorretto-21-alpine AS build
COPY src /service-producer-build/src
COPY pom.xml /service-producer-build
WORKDIR /service-producer-build
RUN mvn -DskipTests clean package

FROM openjdk:21
COPY --from=build /service-producer-build/target/*.jar /service-producer-image/app.jar
WORKDIR /service-producer-image
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]