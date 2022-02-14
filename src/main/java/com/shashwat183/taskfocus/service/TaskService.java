package com.shashwat183.taskfocus.service;

import java.util.List;
import java.util.UUID;

import com.shashwat183.taskfocus.domain.Task;

public interface TaskService {
    public List<Task> getAllTasks();
    public Task getTask(UUID id);
    public void saveTask(Task task);
    public void deleteTask(UUID id);
}
