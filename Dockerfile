FROM alpine/git:1.0.7 as clone
WORKDIR /app
RUN git clone https://github.com/FultonBrowne/Ara-Server.git


FROM openjdk:12-jdk-alpine as build
WORKDIR /app
COPY --from=clone /app/Ara-Server /app
RUN ./gradlew build

FROM openjdk:11.0-jre
WORKDIR /app
COPY --from=build /app/build/libs /app
EXPOSE 80
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar Ara-Server-1.0-SNAPSHOT-all.jar"]
