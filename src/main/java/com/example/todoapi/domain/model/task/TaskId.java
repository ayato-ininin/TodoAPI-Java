package com.example.todoapi.domain.model.task;

// 値オブジェクト
public class TaskId {
    private long value;

    public TaskId(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
