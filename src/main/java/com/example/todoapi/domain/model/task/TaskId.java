package com.example.todoapi.domain.model.task;

// 値オブジェクト
public class TaskId {
    private Long value;

    public TaskId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
