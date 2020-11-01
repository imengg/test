FROM openjdk:8
AND target/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test-0.0.1-SNAPSHOT.jar"]