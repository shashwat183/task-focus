package com.shashwat183.taskfocus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.shashwat183.taskfocus.dao.TaskDAO;
import com.shashwat183.taskfocus.domain.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleTaskService implements TaskService {
    private TaskDAO taskDAO;

    @Autowired
    public SimpleTaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    } 

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    @Override
    public Optional<Task> getTask(UUID id) {
        return taskDAO.findById(id);
    }

    @Override
    public List<Task> getTasksByName(String taskName) {
        return taskDAO.findByTaskName(taskName);
    }

    @Override
    public Task saveTask(Task task) {
        return taskDAO.save(task);
    }

    @Override
    public void deleteTask(UUID id) {
        taskDAO.deleteById(id);
    }
}
