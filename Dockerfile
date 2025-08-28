FROM amazoncorretto:17-alpine
VOLUME /tmp

ADD ./target/AppCuenta-0.0.1-SNAPSHOT.jar AppCuenta.jar
ENV server.app.port=8081
ENV DATASOURCE_URL=jdbc:mysql://mysql_db:3306/db_app
ENTRYPOINT ["java","-jar","/AppCuenta.jar"]