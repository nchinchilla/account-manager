FROM openjdk:8-jdk-alpine
COPY ./target/accounts-manager-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
CMD ["java", "-jar", "accounts-manager-1.0-SNAPSHOT.jar"]
