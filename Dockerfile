FROM openjdk:21
ARG JAR_FILE=target/*.jar
EXPOSE 9001:9001
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
