FROM openjdk:8u131-jre-alpine

COPY ./target/*.jar /home/app.jar

EXPOSE 8010

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*

CMD ["java", "-Dspring.profiles.active=native", "-jar", "/home/app.jar"]