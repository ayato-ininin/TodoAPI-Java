package com.example.todoapi.domain.model.task;

import com.example.todoapi.repository.task.TaskRecord;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<TaskRecord> select(long taskId);

    List<TaskRecord> selectList(int limit, long offset);
    void insert(Task task);

    void update(TaskRecord taskRecord);

    void delete(Long taskId);
}