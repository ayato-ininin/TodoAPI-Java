package com.example.todoapi.domain.model.task;

import com.example.todoapi.repository.task.TaskRecord;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<TaskEntity> find(long taskId);

    List<TaskEntity> findAll(int limit, long offset);
    void insert(TaskEntity taskEntity);

    void update(TaskEntity taskEntity);

    void delete(Long taskId);
}