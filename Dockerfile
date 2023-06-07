FROM amazoncorretto:17-alpine3.16
COPY build/libs/musala-drone-app-0.0.1.jar musala-drone-app-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "musala-drone-app-0.0.1.jar"]