FROM maven:3-eclipse-temurin-11 as maven
WORKDIR /spring/
COPY . .
RUN mvn package --quiet --batch-mode

FROM eclipse-temurin:11-jre
WORKDIR /spring/
COPY --from=maven /spring/spring-jpa/spring-jpa-mysql/target/spring-jpa-mysql-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-server", "-XX:+UseStringDeduplication", "-XX:+UseNUMA", "-XX:+UseParallelGC", "-Dlogging.level.root=OFF", "-jar", "app.jar"]
