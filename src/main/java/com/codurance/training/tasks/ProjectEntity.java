package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class ProjectEntity {

    private int id;
    private String name;
    private List<TaskEntity> taskList;

    public ProjectEntity(int id, String name){
        this.id = id;
        this.name = name;
        this.taskList = new ArrayList<>();
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setTaskList(List<TaskEntity> taskList) {
        this.taskList = taskList;
    }

    public List<TaskEntity> getTaskList() {
        return taskList;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
