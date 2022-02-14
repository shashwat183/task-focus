package com.shashwat183.taskfocus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.shashwat183.taskfocus.domain.Task;
import com.shashwat183.taskfocus.domain.TaskDetails;
import com.shashwat183.taskfocus.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;        
    }
    
    @GetMapping("/api/v1/task")
    public List<Task> listAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("api/v1/task/name/{taskName}")
    public List<Task> getTasksByName(@PathVariable String taskName) {
        return taskService.getTasksByName(taskName);
    }

    @GetMapping("api/v1/task/{id}")
    public Task getTaskById(@PathVariable UUID id) {
        Optional<Task> task = taskService.getTask(id);
        if (!task.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with id %s not found", id));
        }
        return task.get();
    }

    @PostMapping("api/v1/task")
    public Task createTask(@RequestBody TaskDetails taskDetails) {
        Task task = new Task(taskDetails.getTaskName(), taskDetails.getTaskDescription(), taskDetails.getPriority());
        return taskService.saveTask(task);

    }

    @DeleteMapping("api/v1/task/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable UUID id) {
        Optional<Task> task = taskService.getTask(id);
        if (!task.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with id %s not found", id));
        }
        taskService.deleteTask(id);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", String.format("Task with id - %s deleted", id));
        return ResponseEntity.ok().body(responseBody);
    }
    

}
