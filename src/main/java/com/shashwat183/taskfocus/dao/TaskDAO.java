package com.shashwat183.taskfocus.dao;

import java.util.List;
import java.util.UUID;

import com.shashwat183.taskfocus.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<Task, UUID> {

    List<Task> findByTaskName(String taskName);

}