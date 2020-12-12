FROM openjdk:11
ADD target/web-back-0.0.1-SNAPSHOT.jar web-back-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "web-back-0.0.1-SNAPSHOT.jar"]