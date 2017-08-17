FROM openjdk:8u131-jre-alpine

COPY ./target/*.jar /home/app.jar

EXPOSE 8888

CMD ["java", "-jar", "/home/app.jar"]