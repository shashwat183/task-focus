package com.shashwat183.taskfocus.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TaskDetails {
    private String taskName;
    private String taskDescription;
    private int priority;

    public TaskDetails() {}

    public TaskDetails(String taskName, String taskDescription, int priority) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
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
