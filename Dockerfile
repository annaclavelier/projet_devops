FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY pandas-java /app/pandas-java
COPY data /app/data
WORKDIR /app/pandas-java
RUN mvn clean package

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/pandas-java/target/pandas-java-1.0-SNAPSHOT.jar app.jar
COPY data /app/data
ENTRYPOINT ["java", "-cp", "app.jar", "bababooeyz.Demo"]
