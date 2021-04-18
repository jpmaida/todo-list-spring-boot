# todo-list-spring-boot repository

## Description

Repository dedicated to the Todo List backend application. This application is written using Spring Boot, following DDD and TDD principles.

## How to build ?

To build this application execute the following commands: 
```
cd /tmp
git clone https://github.com/jpmaida/todo-list-spring-boot.git
cd todo-list-spring-boot
mvn clean package
```

## How to run ?

To run this application execute the following commands:
```
cd /tmp
git clone https://github.com/jpmaida/todo-list-spring-boot.git
cd todo-list-spring-boot
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dtodo.list.editable=<false|true>"
```
