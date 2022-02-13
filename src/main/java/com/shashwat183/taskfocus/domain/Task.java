package com.shashwat183.taskfocus.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    private UUID id;
    private String taskName;
    private String taskDescription;
    private int priority;

    public Task(UUID id, String taskName, String taskDescription, int priority) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
}
