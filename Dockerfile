FROM openjdk:11
WORKDIR /my-doctor-app
COPY build/libs/*.jar app.jar
EXPOSE 8500
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
