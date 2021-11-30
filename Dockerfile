FROM java:8
COPY target/awards-api-0.0.1-SNAPSHOT.jar /
EXPOSE 8080
CMD ["java", "-jar", "awards-api-0.0.1-SNAPSHOT.jar"]
