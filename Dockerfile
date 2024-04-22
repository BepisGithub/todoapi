FROM gradle:latest as build

WORKDIR /app

COPY . /app


FROM openjdk:17

COPY --from=build /app/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT java -jar app.jar