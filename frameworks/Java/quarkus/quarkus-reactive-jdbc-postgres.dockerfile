FROM maven:3-eclipse-temurin-11 as maven
WORKDIR /quarkus/
COPY . .
RUN mvn package --quiet --batch-mode

FROM eclipse-temurin:11-jre
WORKDIR /quarkus/
COPY --from=maven /quarkus/quarkus-reactive/quarkus-reactive-jdbc/quarkus-reactive-jdbc-postgres/target/quarkus-app/ quarkus-app/
WORKDIR /quarkus/quarkus-app/
EXPOSE 8080
CMD ["java", "-server", "-XX:+UseStringDeduplication", "-XX:+UseNUMA", "-XX:+UseParallelGC", "-jar", "quarkus-run.jar"]