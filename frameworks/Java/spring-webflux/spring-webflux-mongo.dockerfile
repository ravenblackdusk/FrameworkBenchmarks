FROM maven:3.6.1-jdk-11-slim as maven
WORKDIR /spring
COPY pom.xml pom.xml
COPY common/ common/
COPY mongo/ mongo/
COPY r2dbc/ r2dbc/
RUN mvn -pl mongo -am package -q

FROM openjdk:11.0.3-jdk-slim
WORKDIR /spring
COPY --from=maven /spring/mongo/target/mongo-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-server", "-XX:+UseNUMA", "-XX:+UseParallelGC", "-Dlogging.level.root=OFF", "-jar", "app.jar"]
