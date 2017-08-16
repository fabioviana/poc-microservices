FROM openjdk:8u131-jre-alpine

COPY ./target/*.jar /home/app.jar

EXPOSE 8888

CMD ["java", "-Dspring.profiles.active=native", "-jar", "/home/app.jar"]