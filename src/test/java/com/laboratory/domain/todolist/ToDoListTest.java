package com.laboratory.domain.todolist;

import com.laboratory.infra.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Java6Assertions.*;

public class ToDoListTest {

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;
    @MockBean
    private ToDoList toDoList;

    @BeforeEach
    void setup() {
        this.toDoList = new ToDoList();
        toDo1 = new ToDo("task 1");
        toDoList.add(toDo1);
        toDo2 = new ToDo("task 2");
        toDoList.add(toDo2);
        toDo3 = new ToDo("task 3");
        toDoList.add(toDo3);
    }

    @Test
    void generateId(){
        assertThat(IdGenerator.generate()).isGreaterThan(0);
        assertThat(IdGenerator.generate()).isLessThanOrEqualTo(100);
    }

    @Test
    void hasListInTodoList(){
        assertThat(toDoList.getTodoList()).isNotNull();
        assertThat(toDoList.getTodoList()).hasSizeGreaterThan(1);
    }

    @Test
    void addTodoInTodoList(){
        int olderSize = toDoList.size();
        toDoList.add(new ToDo("task X"));
        assertThat(olderSize).isLessThan(toDoList.size());
    }

    @Test
    void removeTodoOfTodoList() {
        ToDo todoReturn = toDoList.remove(toDo2.getId());
        assertThat(todoReturn).isNotNull();
        assertThat(toDoList.getTodoList().get(todoReturn.getId())).isNull();
    }

    @Test
    void isTodoDone(){
        toDoList.markAsDone(toDo2.getId());
        assertThat(toDo2.isDone()).isEqualTo(true);
    }

    @Test
    void isTodoUndone(){
        toDoList.markAsUndone(toDo2.getId());
        assertThat(toDo2.isDone()).isEqualTo(false);
    }

    @Test
    void getSingleToDo(){
        ToDo toDo = toDoList.getToDo(toDo2.getId());
        assertThat(toDo).isNotNull();
        assertThat(toDo.getId()).isEqualTo(toDo2.getId());
    }

    @Test
    void editToDo(){
        ToDo toDo = new ToDo("Bar !!!");
        Long id = toDo2.getId();
        ToDo olderToDo = toDoList.replace(id, toDo);
        assertThat(olderToDo.getDescription()).isEqualTo(toDo2.getDescription());
        assertThat(toDo.getId()).isEqualTo(toDo2.getId());
    }
}