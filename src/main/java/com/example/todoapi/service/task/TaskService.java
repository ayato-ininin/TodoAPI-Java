package com.example.todoapi.service.task;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    public TaskEntity find() {
        return new TaskEntity(1, "from service");
    }
}