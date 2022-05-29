FROM maven:3-eclipse-temurin-11 as maven
WORKDIR /spring
COPY . .
RUN mvn package -q

FROM eclipse-temurin:11-jre
WORKDIR /spring
COPY --from=maven /spring/spring-jdbc/spring-jdbc-postgres/target/spring-jdbc-postgres-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-server", "-XX:+UseNUMA", "-XX:+UseParallelGC", "-Dlogging.level.root=OFF", "-jar", "app.jar"]
