FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /build/libs/transaction-generator-all.jar app.jar
CMD java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$PORT -jar /app.jar