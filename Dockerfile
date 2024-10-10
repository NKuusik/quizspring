FROM eclipse-temurin:21-jdk
EXPOSE 8080
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY ./db_initialization /db_initialization
RUN apt-get update && apt-get -y install python3  \
    && apt-get install -y sqlite3 libsqlite3-dev && apt-get clean
ENTRYPOINT ["java","-jar","/app.jar"]