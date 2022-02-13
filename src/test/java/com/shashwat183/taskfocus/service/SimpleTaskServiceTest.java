package com.shashwat183.taskfocus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.shashwat183.taskfocus.dao.TaskDAO;
import com.shashwat183.taskfocus.domain.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class SimpleTaskServiceTest {
    @Mock
    TaskDAO taskDAO;
    SimpleTaskService simpleTaskService;
    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        this.simpleTaskService = new SimpleTaskService(taskDAO);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(UUID.randomUUID(), "test_task_1", "test_desc", 1));
        tasks.add(new Task(UUID.randomUUID(), "test_task_2", "test_desc", 2));
        Mockito.when(taskDAO.findAll()).thenReturn(tasks);
        List<Task> responseTasks = simpleTaskService.getAllTasks();
        assertThat(responseTasks.size(), is(equalTo(2)));
    }

    @Test
    void saveTasks() {
        UUID id = UUID.randomUUID();
        Task task = new Task(id, "test_task_1", "test_desc", 4);
        simpleTaskService.saveTask(task);
        Mockito.verify(taskDAO).save(task);
    }

    @Test
    void getTask() {
        UUID id = UUID.randomUUID();
        Task testTask = new Task(id, "test_task_1", "test_desc", 3);
        Mockito.when(taskDAO.getById(id)).thenReturn(testTask);
        Task reponseTask = simpleTaskService.getTask(id);
        assertThat(reponseTask, is(equalTo(testTask)));
    }

    @Test
    void deleteTask() {
        UUID id = UUID.randomUUID();
        simpleTaskService.deleteTask(id);
        Mockito.verify(taskDAO).deleteById(id);
    }
}