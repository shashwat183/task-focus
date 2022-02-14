package com.shashwat183.taskfocus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shashwat183.taskfocus.dao.TaskDAO;
import com.shashwat183.taskfocus.domain.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TaskDAO taskDAO;

    List<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<Task>();
        tasks.add(taskDAO.save(new Task("test_task1", "test_desc", 1)));
        tasks.add(taskDAO.save(new Task("test_task2", "test_desc", 3)));
        tasks.add(taskDAO.save(new Task("test_task2", "test_desc_new", 4)));
    }

    @AfterEach
    void tearDown() {
        tasks.clear();
        taskDAO.deleteAll();
    }

    @Test
    void listAllTasks() throws Exception {
        mockMvc.perform(get("/api/v1/task"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].*", hasSize(4)))
        .andExpect(jsonPath("$[1].*", hasSize(4)))
        .andExpect(jsonPath("$[2].*", hasSize(4)));
    }

    @Test
    void getTasksByName() throws Exception {
        mockMvc.perform(get("/api/v1/task/name/test_task2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].taskName", is(equalTo("test_task2"))))
        .andExpect(jsonPath("$[1].taskName", is(equalTo("test_task2"))));

        mockMvc.perform(get("/api/v1/task/name/test_task1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].taskName", is(equalTo("test_task1"))));
    }

    @Test
    void getTasksByNameDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/task/name/test_task_does_not_exist"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getTaskById() throws Exception {
        Task task = tasks.get(0);
        mockMvc.perform(get(String.format("/api/v1/task/%s", task.getId())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.taskName", equalTo(task.getTaskName())));
    }

    @Test
    void getTaskByIdDoesNotExist() throws Exception {
        mockMvc.perform(get(String.format("/api/v1/task/%s", UUID.randomUUID())))
        .andExpect(status().isNotFound());
    }

    @Test
    void createTask() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("taskName", "test_task");
        body.put("taskDescription", "description");
        body.put("priority", 3);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/task")
        .content(objectMapper.writeValueAsString(body))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.id").isString())
        .andExpect(jsonPath("$.taskName", is(equalTo("test_task"))))
        .andExpect(jsonPath("$.taskDescription", is(equalTo("description"))))
        .andExpect(jsonPath("$.priority", is(equalTo(3))));
    }

    @Test
    void deleteTask() throws Exception {
        Task task = tasks.get(0);
        mockMvc.perform(delete(String.format("/api/v1/task/%s", task.getId())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").exists());
        Assertions.assertFalse(taskDAO.findById(task.getId()).isPresent());
    }

    @Test
    void deleteTaskDoesNotExist() throws Exception {
        mockMvc.perform(delete(String.format("/api/v1/task/%s", UUID.randomUUID())))
        .andExpect(status().isNotFound());
    }
}
