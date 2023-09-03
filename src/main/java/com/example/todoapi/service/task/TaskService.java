package com.example.todoapi.service.task;

import com.example.todoapi.repository.task.TaskRecord;
import com.example.todoapi.repository.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskEntity find(long taskId) {
        return taskRepository.select(taskId)
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }

    public List<TaskEntity> find(int limit, long offset) {
        return taskRepository.selectList(limit, offset)
                .stream()
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .collect(Collectors.toList());
    }

    public TaskEntity create(String title) {
        var record = new TaskRecord(null, title);
        taskRepository.insert(record);
        return new TaskEntity(record.getId(), record.getTitle());
    }

    public TaskEntity update(Long taskId, String title) {
        // 更新する前に存在チェックを行う(なければ、not foundExceptionを投げる)
        // 更新する前に存在チェックを行う(なければ、not foundExceptionを投げる)
        taskRepository.select(taskId)
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
        taskRepository.update(new TaskRecord(taskId, title));
        return find(taskId);
    }

    public void delete(Long taskId) {
        // 削除する前に存在チェックを行う(なければ、not foundExceptionを投げる)
        // Optional#orElseThrowは、Optionalが空の場合に引数で指定した例外を投げる
        taskRepository.select(taskId)
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
        taskRepository.delete(taskId);
    }
}
