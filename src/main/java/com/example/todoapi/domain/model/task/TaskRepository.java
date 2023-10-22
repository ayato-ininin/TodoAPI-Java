package com.example.todoapi.domain.model.task;

import com.example.todoapi.models.Task;
import com.example.todoapi.repository.task.TaskRecord;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<Task> select(long taskId);

    List<Task> selectList(int limit, long offset);
    void insert(TaskEntity taskEntity);

    void update(TaskRecord taskRecord);

    void delete(Long taskId);
}