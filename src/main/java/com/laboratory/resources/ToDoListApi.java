package com.laboratory.resources;

import com.laboratory.domain.todolist.ToDo;
import com.laboratory.domain.todolist.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todolist")
public class ToDoListApi {

    @Autowired
    private ToDoList toDoList;

    @Value("${todo-list.editable}")
    private boolean isEditable;

    public ToDoListApi(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @GetMapping(produces = "application/json")
    List<ToDo> all(){
        return this.toDoList.getList();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    ToDo one(@PathVariable Long id){
        return this.toDoList.getToDo(id);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ToDo newToDo(@RequestBody ToDo toDo){
        return this.toDoList.add(toDo);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<ToDo> edit(@PathVariable Long id, @RequestBody ToDo alteredToDo){
        if(isEditable) {
            ToDo olderToDo = this.toDoList.replace(id, alteredToDo);
            return new ResponseEntity<>(olderToDo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id){
        this.toDoList.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/envvars")
    String printEnvVars(){
        return "TODO_LIST_EDITABLE: " + this.isEditable;
    }
}
