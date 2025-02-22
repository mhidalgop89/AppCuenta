FROM amazoncorretto:17-alpine
VOLUME /tmp

ADD ./target/AppCuenta-0.0.1-SNAPSHOT.jar AppCuenta.jar
ENV server.app.port=8081
ENTRYPOINT ["java","-jar","/AppCuenta.jar"]