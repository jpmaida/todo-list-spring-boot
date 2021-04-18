FROM registry.access.redhat.com/ubi8/openjdk-8:1.3

ARG is_todolist_editable
ENV TODO_LIST_EDITABLE=$is_todolist_editable

COPY target/todo-list-backend-0.0.1-SNAPSHOT.jar /home/jboss/todo-list-backend.jar

CMD ["java", "-jar", "/home/jboss/todo-list-backend.jar"]