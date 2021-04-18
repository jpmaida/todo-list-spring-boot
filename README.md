# todo-list-spring-boot repository

## Description

Repository dedicated to the Todo List backend application. This application is written using Spring Boot, following DDD and TDD principles.

### Prerequisites
* Docker
* Maven >= 3.6.0
* Open JDK 8

## How to build ?

To build this application execute the following commands: 
```
cd /tmp
git clone https://github.com/jpmaida/todo-list-spring-boot.git
cd todo-list-spring-boot
mvn clean package
```

## How to run in self-contained mode ?

To run this application execute the following commands:
```
cd /tmp
git clone https://github.com/jpmaida/todo-list-spring-boot.git
cd todo-list-spring-boot
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DTODO_LIST_EDITABLE=<false|true>"
```

## How to run in container (Docker) mode ?

To run this application execute the following commands:
```
cd /tmp
git clone https://github.com/jpmaida/todo-list-spring-boot.git
cd todo-list-spring-boot
mvn clean package
docker build -t <your-namespace>/todo-list-backend:0.0.1-SNAPSHOT --build-arg is_todolist_editable=true .
docker run -p 8080:8080 <your-namespace>/todo-list-backend:0.0.1-SNAPSHOT
```
