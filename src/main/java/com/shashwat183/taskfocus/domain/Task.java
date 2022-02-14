package com.shashwat183.taskfocus.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task extends TaskDetails{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    public Task() {}

    public Task(String taskName, String taskDescription, int priority) {
        super(taskName, taskDescription, priority);
    }

    public Task(UUID id, String taskName, String taskDescription, int priority) {
        super(taskName, taskDescription, priority);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
    
}
