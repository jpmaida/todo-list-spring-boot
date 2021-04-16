package com.laboratory.domain.todolist;

import com.laboratory.infra.IdGenerator;

public class ToDo {
    private Long id;
    private String description;
    private boolean isDone;

    public ToDo(){}

    public ToDo(String description){
        this.id = IdGenerator.generate();
        this.description = description;
        this.isDone = false;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}