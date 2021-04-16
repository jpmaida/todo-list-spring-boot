package com.laboratory.domain.todolist;

import com.laboratory.infra.IdGenerator;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ToDoList {
    private static Map<Long, ToDo> toDoList;

    public ToDoList(){
        toDoList = new HashMap<>();
    }

    public Map<Long, ToDo> getTodoList() {
        return toDoList;
    }

    public ToDo add(ToDo toDo){
        if (toDo.getId() == null)
            toDo.setId(IdGenerator.generate());
        toDoList.put(toDo.getId(), toDo);
        return toDo;
    }

    public ToDo remove(long id) {
        return toDoList.remove(id);
    }

    public void markAsDone(long id){
        ToDo toDo = toDoList.get(id);
        toDo.setIsDone(true);
        toDoList.replace(id, toDo);
    }

    public void markAsUndone(long id){
        ToDo toDo = toDoList.get(id);
        toDo.setIsDone(false);
        toDoList.replace(id, toDo);
    }

    public int size(){
        return toDoList.size();
    }

    public List<ToDo> getList(){
        return new ArrayList<>(toDoList.values());
    }

    public ToDo getToDo(Long id) {
        final ToDo toDo = toDoList.get(id);
        if(toDo == null)
            return new ToDo("");
        return toDo;
    }

    public ToDo replace(Long id, ToDo newToDo) {
        newToDo.setId(id);
        return toDoList.replace(id, newToDo);
    }
}