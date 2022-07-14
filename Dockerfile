FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_javarush_sergeyingit_bot
ENV BOT_TOKEN=5413233267:AAFYR0BW4XgRGOSP0qhQeNiQgZT-C_Y-KSE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}","-jar","/app.jar"]