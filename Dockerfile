FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
EXPOSE 8089/tcp
COPY build/libs/*-all.jar MicronautGroovy.jar
CMD java ${JAVA_OPTS} -jar MicronautGroovy.jar
