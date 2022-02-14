package com.shashwat183.taskfocus.service;

import java.util.List;
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
    public Task getTask(UUID id) {
        return taskDAO.getById(id);
    }

    @Override
    public void saveTask(Task task) {
        taskDAO.save(task);
    }

    @Override
    public void deleteTask(UUID id) {
        taskDAO.deleteById(id);
    }
    
}
