Clicker
======
## Тестовое приложение

#### Требуемое ПО
 - JDK 8+
 - Maven
 - IntelliJ IDEA (не ниже 2018.2.1)
 - Postgres
 - GitBash(опционально)
 - Docker(опционально)
 
##### Вариант сборки 1(Docker)
- собрать проект с помощью `100_rebuild_back.sh`
- запустить контейнер с postgres `docker -D run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres`
- собрать образ приложения `docker build -t clicker-app .`
- запустить приложение `docker -D run --name clicker --network=host -d -p 8080:8080 clicker-app`
- остановить приложение `docker stop id_container`
- удалить контейнер `docker rm id_container`
 
##### Вариант сборки 2(Docker внутри гостевой ОС)
- собрать проект с помощью `100_rebuild_back.sh`
- создать docker swarm `docker swarm init`
- создать network чтоб была возможность подключаться извне `docker network create -d overlay --attachable overlay`
- запустить контейнер с postgres `docker -D run --name some-postgres --network=overlay -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres`
- скопировать конфиг в Dockerfile:
>FROM openjdk:11
>
>WORKDIR /clicker-app/
>
>COPY ./target/clicker-0.0.1-SNAPSHOT.jar clicker.jar
>
>COPY /docker/entrypoint.sh entrypoint.sh
>
>COPY ./override.properties override.properties
>
>RUN chmod 755 entrypoint.sh
>
>RUN ls -al
>
>ENV JAVA_OPTS=""
>
>ENV BOOT_OPTS="--spring.config.additional-location=./override.properties"
>
>EXPOSE 8080
>
>CMD ["bash", "-x", "./entrypoint.sh"]
- создать в корне проекта проперти `override.properties` и переопределить параметры(чтоб в `spring.datasource.url` host совпадал с названем контейнера)
>####### dataSource
>
>spring.datasource.url=jdbc:postgresql://some-postgres:5432/postgres
>
>spring.datasource.driverClassName=org.postgresql.Driver
>
>spring.datasource.username=postgres
>
>spring.datasource.password=mysecretpassword
>
>management.endpoints.web.exposure.include=*
>
>management.endpoint.shutdown.enabled=true
>
>endpoints.shutdown.enabled=true
- собрать образ приложения `docker build -t clicker-app .`
- запустить приложение `docker -D run --name clicker --network=overlay -d -p 8080:8080 clicker-app`
- остановить приложение `docker stop id_container`
- удалить контейнер `docker rm id_container`
 
 ##### Вариант сборки 3
 - собрать проект с помощью `100_rebuild_back.sh` или `mvnw.cmd clean package`
 - запустить приложение `400_start_back.sh` или `mvnw.cmd spring-boot:run` или `java -jar clicker-0.0.1-SNAPSHOT.jar`
 - остановить приложение `500_stop_back.sh` или `curl -X POST localhost:8080/actuator/shutdown`

------
#### Открыть в браузере `http://localhost:8080/`

#### Или выполнить в терминале  `curl -v http://localhost:8080/`, `curl -v -X POST http://localhost:8080/increment`
