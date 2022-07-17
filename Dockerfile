FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_javarush_sergeyingit_bot
ENV BOT_TOKEN=5413233267:AAFYR0BW4XgRGOSP0qhQeNiQgZT-C_Y-KSE
ENV BOT_DB_USERNAME=user
ENV BOT_DB_PASSWORD=password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-Dspring.datasource.password=${BOT_DB_PASSWORD}","-jar","/app.jar"]