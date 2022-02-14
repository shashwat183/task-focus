package com.shashwat183.taskfocus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.shashwat183.taskfocus.domain.Task;

public interface TaskService {
    public List<Task> getAllTasks();
    public List<Task> getTasksByName(String taskName);
    public Optional<Task> getTask(UUID id);
    public Task saveTask(Task task);
    public void deleteTask(UUID id);
}
