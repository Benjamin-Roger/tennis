FROM adoptopenjdk/maven-openjdk11 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:11

COPY --from=builder /usr/src/app/target/back-0.0.1-SNAPSHOT.jar /usr/app/back-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/usr/app/back-0.0.1-SNAPSHOT.jar"]
