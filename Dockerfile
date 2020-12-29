FROM openjdk:11

WORKDIR /clicker-app/

COPY ./target/clicker-0.0.1-SNAPSHOT.jar clicker.jar

COPY /docker/entrypoint.sh entrypoint.sh

RUN chmod 755 entrypoint.sh

RUN ls -al

ENV JAVA_OPTS=""

ENV BOOT_OPTS=""

EXPOSE 8080

CMD ["bash", "-x", "./entrypoint.sh"]