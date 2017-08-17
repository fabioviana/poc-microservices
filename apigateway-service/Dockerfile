FROM openjdk:8u131-jre-alpine

COPY ./target/*.jar /home/app.jar

EXPOSE 9000

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*

CMD ["java", "-jar", "/home/app.jar"]