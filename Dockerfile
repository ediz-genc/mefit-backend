FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
WORKDIR /app
COPY target/mefit-0.0.1-SNAPSHOT.jar /app/mefit.jar
EXPOSE 8080
CMD ["java", "-jar", "mefit.jar"]