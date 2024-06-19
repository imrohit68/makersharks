FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/makersharks-assessment-0.0.1-SNAPSHOT.jar makersharks.jar
EXPOSE 8080
CMD ["java","-jar","makersharks.jar"]